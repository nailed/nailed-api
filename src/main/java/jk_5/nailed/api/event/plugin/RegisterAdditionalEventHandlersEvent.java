package jk_5.nailed.api.event.plugin;

import jk_5.eventbus.EventBus;

/**
 * If you want to register more event handlers, this is the only moment you can do it.
 * By default the {@link jk_5.nailed.api.plugin.Plugin} class is already registered in the global and plugin bus,
 * but you can register additional handlers using this event.
 * This event will be called as quick as possible after the plugins have been loaded.
 *
 * @author jk-5
 */
public class RegisterAdditionalEventHandlersEvent extends PluginEvent {

    private final EventBus pluginBus;
    private final EventBus globalBus;

    public RegisterAdditionalEventHandlersEvent(EventBus pluginBus, EventBus globalBus) {
        this.pluginBus = pluginBus;
        this.globalBus = globalBus;
    }

    public void register(Object obj){
        pluginBus.register(obj);
        globalBus.register(obj);
    }
}
