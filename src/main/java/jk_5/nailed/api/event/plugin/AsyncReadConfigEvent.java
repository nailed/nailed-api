package jk_5.nailed.api.event.plugin;

import java.io.File;

/**
 * If you need to read config for your plugin, do it in this event.
 * Each event will be executed in a seperate thread, and the loading of the server will wait until all threads are done.
 * Note: It is possible that this event is being called a second time, even when the server is already running.
 *       This can be caused by some kind of reload command.
 *
 * @author jk-5
 */
public class AsyncReadConfigEvent extends PluginEvent {

    private final File pluginDirectory;

    public AsyncReadConfigEvent(File pluginDirectory) {
        this.pluginDirectory = pluginDirectory;
    }

    public File getPluginDirectory(){
        return pluginDirectory;
    }
}
