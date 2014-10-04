package jk_5.nailed.example.plugin;

import com.typesafe.config.Config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jk_5.eventbus.EventHandler;
import jk_5.nailed.api.chat.TextComponent;
import jk_5.nailed.api.command.Command;
import jk_5.nailed.api.command.CommandSender;
import jk_5.nailed.api.command.parametric.annotation.Text;
import jk_5.nailed.api.event.RegisterCommandsEvent;
import jk_5.nailed.api.event.plugin.RegisterAdditionalEventHandlersEvent;
import jk_5.nailed.api.player.Player;
import jk_5.nailed.api.plugin.Configuration;
import jk_5.nailed.api.plugin.Plugin;
import jk_5.nailed.api.plugin.PluginIdentifier;

/**
 * No description given
 *
 * @author jk-5
 */
@Plugin(id = "PrivateMessagePlugin", name = "Private Messages Plugin", version = "1.0.0")
public class PrivateMessagePlugin {

    public Logger logger = LogManager.getLogger(PrivateMessagePlugin.class);

    @Configuration(defaults = "privmsgplugin.conf")
    public Config config;

    @PluginIdentifier.Instance
    public PluginIdentifier identifier;

    @EventHandler
    public void registerCommands(RegisterCommandsEvent event){
        event.registerCommandClass(this);

        event.subcommand("test").subcommand("test2").registerCommandClass(this);
    }

    @EventHandler
    public void registerHandlers(RegisterAdditionalEventHandlersEvent event){
        logger.info("str: " + config.getString("teststring"));
        logger.info("int: " + config.getString("testnumber"));
        logger.info("Identifier: " + identifier.toString());
    }

    @Command(aliases = "msg", desc = "Send a message to a user")
    public void sendPrivateMessage(CommandSender sender, Player target, @Text String message){
        target.sendMessage(new TextComponent(sender.getName() + ": " + message));
    }
}
