/*
 * Nailed, a Minecraft PvP server framework
 * Copyright (C) jk-5 <http://github.com/jk-5/>
 * Copyright (C) Nailed team and contributors <http://github.com/nailed/>
 *
 * This program is free software: you can redistribute it and/or modify it
 * under the terms of the MIT License.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License
 * for more details.
 *
 * You should have received a copy of the MIT License along with
 * this program. If not, see <http://opensource.org/licenses/MIT/>.
 */

package jk_5.nailed.api.plugin

import java.io.{File, InputStream, InputStreamReader}
import java.net.{URL, URLClassLoader}
import java.util
import java.util.jar.JarFile
import java.util.regex.Pattern

import com.google.common.collect.{ArrayListMultimap, Multimap}
import com.google.gson.Gson
import jk_5.eventbus.{Event, EventBus}
import org.apache.logging.log4j.LogManager

import scala.collection.mutable
import scala.util.Properties

/**
 * No description given
 *
 * @author jk-5
 */
class PluginManager(private val server: Server) {

  private val eventBus = new EventBus
  private val plugins = mutable.HashMap[String, Plugin]()
  private val toLoad = mutable.HashMap[String, PluginDescription]()
  private val commandMap = mutable.HashMap[String, Command]()
  private val commandsByPlugin: Multimap[Plugin, Command] = ArrayListMultimap.create()
  private val commands = mutable.HashSet[Command]()
  private val listenersByPlugin: Multimap[Plugin, Any] = ArrayListMultimap.create()
  private val argsSplit = Pattern.compile(" ")
  private val logger = LogManager.getLogger
  private val gson = new Gson
  private val loader = this.getClass.getClassLoader.asInstanceOf[URLClassLoader]
  private lazy val addUrlMethod = {
    val v = classOf[URLClassLoader].getDeclaredMethod("addURL", classOf[URL])
    v.setAccessible(true)
    v
  }
  private final val internalPlugins = "internalplugin.json,directorymappackloaderplugin.json,mapplugin.json,worldeditplugin.json"

  /**
   * Register a command so that it may be executed.
   *
   * @param plugin the plugin owning this command
   * @param command the command to register
   */
  def registerCommand(plugin: Plugin, command: Command){
    commandMap.put(command.getName.toLowerCase, command)
    command.getAliases.foreach(a => commandMap.put(a.toLowerCase, command))
    commandsByPlugin.put(plugin, command)
    commands += command
  }

  /**
   * Unregister a command so it will no longer be executed.
   *
   * @param command the command to unregister
   */
  def unregisterCommand(command: Command){
    val removals = mutable.ArrayBuffer[String]()
    commandMap.foreach(e => if(e._2 == command) removals += e._1)
    removals.foreach(commandMap.remove)
    commandsByPlugin.values().remove(command)
    commands.remove(command)
  }

  /**
   * Unregister all commands owned by a {@link Plugin}
   *
   * @param plugin the plugin to unregister the commands of
   */
  def unregisterCommands(plugin: Plugin){
    val it = commandsByPlugin.get(plugin).iterator()
    while(it.hasNext){
      val c = it.next()
      val removals = mutable.ArrayBuffer[String]()
      commandMap.foreach(e => if(e._2 == c) removals += e._1)
      removals.foreach(commandMap.remove)
      commands.remove(c)
      it.remove()
    }
  }

  def dispatchCommand(sender: CommandSender, line: String): Int = dispatchCommand(sender, line, null)
  def dispatchCommand(sender: CommandSender, line: String, tabResults: mutable.ListBuffer[String]): Int = {
    //if(Server.getInstance.isAsync) throw new IllegalStateException("Async command dispatch")
    val split = argsSplit.split(line, -1)
    val isCompleteRequest = tabResults != null
    if(split.length == 0) return -1
    val commandName = if(sender.isInstanceOf[Player] && !isCompleteRequest) split(0).substring(1).toLowerCase else split(0).toLowerCase
    val command = commandMap.get(commandName)
    if(command.isEmpty){
      if(isCompleteRequest && split.length == 1){
        val cmd = commandMap.filter(c => c._1.startsWith(split(0)))
        if(cmd.size > 0){
          tabResults ++= cmd.keySet
          return 1
        }else{
          return -1
        }
      }else{
        return -1
      }
    }
    val args = util.Arrays.copyOfRange(split, 1, split.length)
    try {
      if (!isCompleteRequest) {
        val ctx = new CommandContext(sender)
        command.get.execute(ctx, new Arguments(ctx, args))
        ctx.getAnalogOutput
      } else command.get match {
        case t: TabExecutor =>
          tabResults ++= t.onTabComplete(sender, args)
          1
        case _ => 1
      }
    }catch{
      case e: CommandException =>
        sender.sendMessage(new ComponentBuilder(e.getMessage).color(ChatColor.RED).create())
        0
      case e: Exception =>
        sender.sendMessage(new ComponentBuilder("An internal error occurred whilst executing this command, please check the console log for details.").color(ChatColor.RED).create())
        logger.warn("Error while dispatching command", e)
        0
    }
  }

  /**
   * Returns the {@link Plugin} objects corresponding to all loaded plugins.
   *
   * @return the set of loaded plugins
   */
  def getPlugins = this.plugins.values

  /**
   * Returns a loaded plugin identified by the specified name.
   *
   * @param name of the plugin to retrieve
   * @return the retrieved plugin or null if not loaded
   */
  def getPlugin(name: String) = this.plugins.get(name).orNull

  /**
   * Load all plugins from the specified folder.
   *
   * @param folder the folder to search for plugins in
   */
  def discoverPlugins(folder: File){
    Checks.notNull(folder, "folder")
    Checks.check(folder.isDirectory, "Must load from a directory")

    for(file <- folder.listFiles()){
      if(file.isFile && file.getName.endsWith(".jar")){
        var jar: JarFile = null
        try{
          jar = new JarFile(file)
          val pdf = jar.getJarEntry("plugin.json")
          Checks.notNull(pdf, "Plugin must have a plugin.json file")

          var is: InputStream = null
          try{
            is = jar.getInputStream(pdf)
            val desc = gson.fromJson(new InputStreamReader(is), classOf[PluginDescription])
            desc.setFile(file)
            toLoad.put(desc.getName, desc)
          }finally{
            is.close()
          }
        }catch{
          case e: Exception => logger.warn("Could not load plugin from file " + file, e)
        }finally{
          if(jar != null) jar.close()
        }
      }
    }
  }

  /**
   * Discovers plugins from the classpath
   * It checks the nailed.runtimePlugins property which should be set to the location of the plugin.json on the classpath
   */
  def discoverClasspathPlugins(){
    var prop = Properties.propOrEmpty("nailed.runtimePlugins")
    if(prop.isEmpty) prop = this.internalPlugins else prop += "," + this.internalPlugins
    val plugins = prop.trim.split(",")
    for(plugin <- plugins){
      val is = getClass.getClassLoader.getResourceAsStream(plugin.trim)
      if(is == null){
        logger.warn("Plugin description file {} could not be found on the classpath. Skipping the plugin", plugin.trim)
      }else{
        val desc = gson.fromJson(new InputStreamReader(is), classOf[PluginDescription])
        desc.setFile(null)
        toLoad.put(desc.getName, desc)
      }
    }
  }

  def loadPlugins(){
    val pluginStatuses = mutable.HashMap[PluginDescription, java.lang.Boolean]()
    for(e <- toLoad){
      val plugin = e._2
      if(!enablePlugin(pluginStatuses, new mutable.Stack[PluginDescription](), plugin)){
        logger.warn("Failed to enable " + e._1)
      }
    }
    toLoad.clear()
    //toLoad = null
  }

  def enablePlugins(){
    for(plugin <- plugins.values){
      try{
        plugin.onEnable()
        logger.info("Enabled plugin {} version {} by {}", plugin.getDescription.getName, plugin.getDescription.getVersion, plugin.getDescription.getAuthor)
      }catch{
        case e: Exception => logger.warn("Exception encountered when loading plugin: " + plugin.getDescription.getName, e)
      }
    }
  }

  def enablePlugin(pluginStatuses: mutable.HashMap[PluginDescription, java.lang.Boolean], dependencyStack: mutable.Stack[PluginDescription], plugin: PluginDescription): Boolean = {
    if(pluginStatuses.contains(plugin)) pluginStatuses.get(plugin).get
    val dependencies = mutable.HashSet[String]()
    dependencies ++= plugin.getDepends
    dependencies ++= plugin.getSoftDepends
    var status = true

    for(dependencyName <- dependencies if status){
      val depend = toLoad.get(dependencyName)
      var dependStatus: java.lang.Boolean = if(depend.isDefined) pluginStatuses.get(depend.get).orNull else java.lang.Boolean.FALSE
      if(dependStatus == null){
        if(dependencyStack.contains(depend)){
          val dependencyGraph = new StringBuilder
          for(element <- dependencyStack){
            dependencyGraph.append(element.getName).append(" -> ")
          }
          dependencyGraph.append(plugin.getName).append(" -> ").append(dependencyName)
          logger.warn("Circular dependency detected: " + dependencyGraph)
          status = false
        }else{
          dependencyStack.push(plugin)
          dependStatus = this.enablePlugin(pluginStatuses, dependencyStack, depend.get)
          dependencyStack.pop()
        }
      }
      if(dependStatus == java.lang.Boolean.FALSE && plugin.getDepends.contains(dependencyName)){ //Only fail if this wasn't a soft dependency
        logger.warn("{} (required by {}) is unavailable", dependencyName, plugin.getName)
        status = false
      }
    }

    if(status) try{
      if(plugin.getFile != null) addUrlMethod.invoke(loader, plugin.getFile.toURI.toURL)
      val main = loader.loadClass(plugin.getMain)
      val cl = main.newInstance().asInstanceOf[Plugin]
      cl.init(server, plugin)
      plugins.put(plugin.getName, cl)
      cl.onLoad()
      logger.info("Loaded plugin {} version {} by {}", plugin.getName, plugin.getVersion, plugin.getAuthor)
    }catch{
      case t: Throwable =>
        logger.warn("Error loading plugin " + plugin.getName, t)
    }
    pluginStatuses.put(plugin, status)
    status
  }

  /**
   * Register a listener for receiving called events. Methods in this
   * Object which wish to receive events must be annotated with the
   * {@link EventHandler} annotation.
   *
   * @param plugin the owning plugin
   * @param listener the listener to register events for
   */
  def registerListener(plugin: Plugin, listener: Any){
    eventBus.register(listener)
    listenersByPlugin.put(plugin, listener)
  }

  /**
   * Unregister a listener so that the events do not reach it anymore.
   *
   * @param listener the listener to unregister
   */
  def unregisterListener(listener: Any){
    eventBus.unregister(listener)
    listenersByPlugin.values.remove(listener)
  }

  /**
   * Dispatch an event to all subscribed listeners and return the event once
   * it has been handled by these listeners.
   *
   * @tparam T the type bounds, must be a class which extends event
   * @param event the event to call
   * @return the called event
   */
  def callEvent[T <: Event](event: T): T = {
    Checks.notNull(event, "event")
    val start = System.nanoTime()
    eventBus.post(event)
    val elapsed = start - System.nanoTime()
    if(elapsed > 250000){
      logger.warn("Event {} took {}ns to process!", event.toString, elapsed.toString)
    }
    event
  }

  /**
   * Unregister all of a Plugin's listener.
   *
   * @param plugin the plugin to unregister all listeners off
   */
  def unregisterListeners(plugin: Plugin){
    val it = listenersByPlugin.get(plugin).iterator
    while(it.hasNext){
      eventBus.unregister(it.next())
      it.remove()
    }
  }

  def getAllCommands: Array[Command] = this.commands.toArray
}