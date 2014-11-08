package jk_5.nailed.api.messaging;

import javax.annotation.Nonnull;

import jk_5.nailed.api.plugin.PluginContainer;

/**
 * No description given
 *
 * @author jk-5
 */
public class PluginMessageListenerRegistration {

    private final Messenger messenger;
    private final PluginContainer plugin;
    private final String channel;
    private final PluginMessageListener listener;

    public PluginMessageListenerRegistration(@Nonnull Messenger messenger, @Nonnull PluginContainer plugin, @Nonnull String channel, @Nonnull PluginMessageListener listener) {
        this.messenger = messenger;
        this.plugin = plugin;
        this.channel = channel;
        this.listener = listener;
    }

    public PluginContainer getPlugin() {
        return plugin;
    }

    public String getChannel() {
        return channel;
    }

    public PluginMessageListener getListener() {
        return listener;
    }

    public boolean isValid(){
        return this.messenger.isRegistrationValid(this);
    }

    @Override
    public boolean equals(Object o) {
        if(this == o){
            return true;
        }
        if(o == null || getClass() != o.getClass()){
            return false;
        }

        PluginMessageListenerRegistration that = (PluginMessageListenerRegistration) o;

        if(!channel.equals(that.channel)){
            return false;
        }
        if(!listener.equals(that.listener)){
            return false;
        }
        if(!messenger.equals(that.messenger)){
            return false;
        }
        if(!plugin.equals(that.plugin)){
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = messenger.hashCode();
        result = 31 * result + plugin.hashCode();
        result = 31 * result + channel.hashCode();
        result = 31 * result + listener.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "PluginMessageListenerRegistration{" +
                "messenger=" + messenger +
                ", plugin=" + plugin +
                ", channel='" + channel + '\'' +
                ", listener=" + listener +
                '}';
    }
}
