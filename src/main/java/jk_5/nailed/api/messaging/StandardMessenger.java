package jk_5.nailed.api.messaging;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.google.common.collect.ImmutableSet;

import jk_5.nailed.api.Platform;
import jk_5.nailed.api.player.Player;
import jk_5.nailed.api.plugin.PluginContainer;
import jk_5.nailed.api.plugin.PluginIdentifier;

/**
 * No description given
 *
 * @author jk-5
 */
public class StandardMessenger implements Messenger {

    private final Map<String, Set<PluginMessageListenerRegistration>> incomingByChannel = new HashMap<String, Set<PluginMessageListenerRegistration>>();
    private final Map<PluginIdentifier, Set<PluginMessageListenerRegistration>> incomingByPlugin = new HashMap<PluginIdentifier, Set<PluginMessageListenerRegistration>>();
    private final Map<String, Set<PluginIdentifier>> outgoingByChannel = new HashMap<String, Set<PluginIdentifier>>();
    private final Map<PluginIdentifier, Set<String>> outgoingByPlugin = new HashMap<PluginIdentifier, Set<String>>();
    private final Object incomingLock = new Object();
    private final Object outgoingLock = new Object();
    private final Platform platform;

    public StandardMessenger(Platform platform) {
        this.platform = platform;
    }

    private void addToOutgoing(PluginIdentifier plugin, String channel) {
        synchronized (outgoingLock) {
            Set<PluginIdentifier> plugins = outgoingByChannel.get(channel);
            Set<String> channels = outgoingByPlugin.get(plugin);

            if (plugins == null) {
                plugins = new HashSet<PluginIdentifier>();
                outgoingByChannel.put(channel, plugins);
            }

            if (channels == null) {
                channels = new HashSet<String>();
                outgoingByPlugin.put(plugin, channels);
            }

            plugins.add(plugin);
            channels.add(channel);
        }
    }

    private void removeFromOutgoing(PluginIdentifier plugin, String channel) {
        synchronized (outgoingLock) {
            Set<PluginIdentifier> plugins = outgoingByChannel.get(channel);
            Set<String> channels = outgoingByPlugin.get(plugin);

            if (plugins != null) {
                plugins.remove(plugin);

                if (plugins.isEmpty()) {
                    outgoingByChannel.remove(channel);
                }
            }

            if (channels != null) {
                channels.remove(channel);

                if (channels.isEmpty()) {
                    outgoingByChannel.remove(channel);
                }
            }
        }
    }

    private void removeFromOutgoing(PluginIdentifier plugin) {
        synchronized (outgoingLock) {
            Set<String> channels = outgoingByPlugin.get(plugin);

            if (channels != null) {
                String[] toRemove = channels.toArray(new String[0]);

                outgoingByPlugin.remove(plugin);

                for (String channel : toRemove) {
                    removeFromOutgoing(plugin, channel);
                }
            }
        }
    }

    private void addToIncoming(PluginMessageListenerRegistration registration) {
        synchronized (incomingLock) {
            Set<PluginMessageListenerRegistration> registrations = incomingByChannel.get(registration.getChannel());

            if (registrations == null) {
                registrations = new HashSet<PluginMessageListenerRegistration>();
                incomingByChannel.put(registration.getChannel(), registrations);
            } else {
                if (registrations.contains(registration)) {
                    throw new IllegalArgumentException("This registration already exists");
                }
            }

            registrations.add(registration);

            registrations = incomingByPlugin.get(registration.getPlugin().getIdentifier());

            if (registrations == null) {
                registrations = new HashSet<PluginMessageListenerRegistration>();
                incomingByPlugin.put(registration.getPlugin().getIdentifier(), registrations);
            } else {
                if (registrations.contains(registration)) {
                    throw new IllegalArgumentException("This registration already exists");
                }
            }

            registrations.add(registration);
        }
    }

    private void removeFromIncoming(PluginMessageListenerRegistration registration) {
        synchronized (incomingLock) {
            Set<PluginMessageListenerRegistration> registrations = incomingByChannel.get(registration.getChannel());

            if (registrations != null) {
                registrations.remove(registration);

                if (registrations.isEmpty()) {
                    incomingByChannel.remove(registration.getChannel());
                }
            }

            registrations = incomingByPlugin.get(registration.getPlugin().getIdentifier());

            if (registrations != null) {
                registrations.remove(registration);

                if (registrations.isEmpty()) {
                    incomingByPlugin.remove(registration.getPlugin().getIdentifier());
                }
            }
        }
    }

    private void removeFromIncoming(PluginContainer plugin, String channel) {
        synchronized (incomingLock) {
            Set<PluginMessageListenerRegistration> registrations = incomingByPlugin.get(plugin.getIdentifier());

            if (registrations != null) {
                PluginMessageListenerRegistration[] toRemove = registrations.toArray(new PluginMessageListenerRegistration[0]);

                for (PluginMessageListenerRegistration registration : toRemove) {
                    if (registration.getChannel().equals(channel)) {
                        removeFromIncoming(registration);
                    }
                }
            }
        }
    }

    private void removeFromIncoming(PluginContainer plugin) {
        synchronized (incomingLock) {
            Set<PluginMessageListenerRegistration> registrations = incomingByPlugin.get(plugin.getIdentifier());

            if (registrations != null) {
                PluginMessageListenerRegistration[] toRemove = registrations.toArray(new PluginMessageListenerRegistration[0]);

                incomingByPlugin.remove(plugin.getIdentifier());

                for (PluginMessageListenerRegistration registration : toRemove) {
                    removeFromIncoming(registration);
                }
            }
        }
    }

    @Override
    public boolean isReservedChannel(String channel) {
        validateChannel(channel);

        return channel.equals("REGISTER") || channel.equals("UNREGISTER");
    }

    @Override
    public void registerOutgoingPluginChannel(PluginIdentifier plugin, String channel) {
        if (plugin == null) {
            throw new IllegalArgumentException("Plugin cannot be null");
        }
        validateChannel(channel);
        if (isReservedChannel(channel)) {
            throw new ReservedChannelException(channel);
        }

        addToOutgoing(plugin, channel);
    }

    @Override
    public void unregisterOutgoingPluginChannel(PluginIdentifier plugin, String channel) {
        if (plugin == null) {
            throw new IllegalArgumentException("Plugin cannot be null");
        }
        validateChannel(channel);

        removeFromOutgoing(plugin, channel);
    }

    @Override
    public void unregisterOutgoingPluginChannel(PluginIdentifier plugin) {
        if (plugin == null) {
            throw new IllegalArgumentException("Plugin cannot be null");
        }

        removeFromOutgoing(plugin);
    }

    @Override
    public PluginMessageListenerRegistration registerIncomingPluginChannel(PluginIdentifier plugin, String channel, PluginMessageListener listener) {
        if (plugin == null) {
            throw new IllegalArgumentException("Plugin cannot be null");
        }
        PluginContainer container = platform.getPluginManager().getPlugin(plugin);
        if (container == null) {
            throw new IllegalArgumentException("PluginIdentifier points to an unknown plugin");
        }
        validateChannel(channel);
        if (isReservedChannel(channel)) {
            throw new ReservedChannelException(channel);
        }
        if (listener == null) {
            throw new IllegalArgumentException("Listener cannot be null");
        }

        PluginMessageListenerRegistration result = new PluginMessageListenerRegistration(this, container, channel, listener);

        addToIncoming(result);

        return result;
    }

    @Override
    public void unregisterIncomingPluginChannel(PluginIdentifier plugin, String channel, PluginMessageListener listener) {
        if (plugin == null) {
            throw new IllegalArgumentException("Plugin cannot be null");
        }
        if (listener == null) {
            throw new IllegalArgumentException("Listener cannot be null");
        }
        PluginContainer container = platform.getPluginManager().getPlugin(plugin);
        if (container == null) {
            throw new IllegalArgumentException("PluginIdentifier points to an unknown plugin");
        }
        validateChannel(channel);

        removeFromIncoming(new PluginMessageListenerRegistration(this, container, channel, listener));
    }

    @Override
    public void unregisterIncomingPluginChannel(PluginIdentifier plugin, String channel) {
        if (plugin == null) {
            throw new IllegalArgumentException("Plugin cannot be null");
        }
        PluginContainer container = platform.getPluginManager().getPlugin(plugin);
        if (container == null) {
            throw new IllegalArgumentException("PluginIdentifier points to an unknown plugin");
        }
        validateChannel(channel);

        removeFromIncoming(container, channel);
    }

    @Override
    public void unregisterIncomingPluginChannel(PluginIdentifier plugin) {
        if (plugin == null) {
            throw new IllegalArgumentException("Plugin cannot be null");
        }
        PluginContainer container = platform.getPluginManager().getPlugin(plugin);
        if (container == null) {
            throw new IllegalArgumentException("PluginIdentifier points to an unknown plugin");
        }

        removeFromIncoming(container);
    }

    @Override
    public Set<String> getOutgoingChannels() {
        synchronized (outgoingLock) {
            Set<String> keys = outgoingByChannel.keySet();
            return ImmutableSet.copyOf(keys);
        }
    }

    @Override
    public Set<String> getOutgoingChannels(PluginIdentifier plugin) {
        if (plugin == null) {
            throw new IllegalArgumentException("Plugin cannot be null");
        }

        synchronized (outgoingLock) {
            Set<String> channels = outgoingByPlugin.get(plugin);

            if (channels != null) {
                return ImmutableSet.copyOf(channels);
            } else {
                return ImmutableSet.of();
            }
        }
    }

    @Override
    public Set<String> getIncomingChannels() {
        synchronized (incomingLock) {
            Set<String> keys = incomingByChannel.keySet();
            return ImmutableSet.copyOf(keys);
        }
    }

    @Override
    public Set<String> getIncomingChannels(PluginIdentifier plugin) {
        if (plugin == null) {
            throw new IllegalArgumentException("Plugin cannot be null");
        }

        synchronized (incomingLock) {
            Set<PluginMessageListenerRegistration> registrations = incomingByPlugin.get(plugin);

            if (registrations != null) {
                ImmutableSet.Builder<String> builder = ImmutableSet.builder();

                for (PluginMessageListenerRegistration registration : registrations) {
                    builder.add(registration.getChannel());
                }

                return builder.build();
            } else {
                return ImmutableSet.of();
            }
        }
    }

    @Override
    public Set<PluginMessageListenerRegistration> getIncomingChannelRegistrations(PluginIdentifier plugin) {
        if (plugin == null) {
            throw new IllegalArgumentException("Plugin cannot be null");
        }

        synchronized (incomingLock) {
            Set<PluginMessageListenerRegistration> registrations = incomingByPlugin.get(plugin);

            if (registrations != null) {
                return ImmutableSet.copyOf(registrations);
            } else {
                return ImmutableSet.of();
            }
        }
    }

    @Override
    public Set<PluginMessageListenerRegistration> getIncomingChannelRegistrations(String channel) {
        validateChannel(channel);

        synchronized (incomingLock) {
            Set<PluginMessageListenerRegistration> registrations = incomingByChannel.get(channel);

            if (registrations != null) {
                return ImmutableSet.copyOf(registrations);
            } else {
                return ImmutableSet.of();
            }
        }
    }

    @Override
    public Set<PluginMessageListenerRegistration> getIncomingChannelRegistrations(PluginIdentifier plugin, String channel) {
        if (plugin == null) {
            throw new IllegalArgumentException("Plugin cannot be null");
        }
        validateChannel(channel);

        synchronized (incomingLock) {
            Set<PluginMessageListenerRegistration> registrations = incomingByPlugin.get(plugin);

            if (registrations != null) {
                ImmutableSet.Builder<PluginMessageListenerRegistration> builder = ImmutableSet.builder();

                for (PluginMessageListenerRegistration registration : registrations) {
                    if (registration.getChannel().equals(channel)) {
                        builder.add(registration);
                    }
                }

                return builder.build();
            } else {
                return ImmutableSet.of();
            }
        }
    }

    @Override
    public boolean isRegistrationValid(PluginMessageListenerRegistration registration) {
        if (registration == null) {
            throw new IllegalArgumentException("Registration cannot be null");
        }

        synchronized (incomingLock) {
            Set<PluginMessageListenerRegistration> registrations = incomingByPlugin.get(registration.getPlugin().getIdentifier());

            if (registrations != null) {
                return registrations.contains(registration);
            }

            return false;
        }
    }

    @Override
    public boolean isIncomingChannelRegistered(PluginIdentifier plugin, String channel) {
        if (plugin == null) {
            throw new IllegalArgumentException("Plugin cannot be null");
        }
        validateChannel(channel);

        synchronized (incomingLock) {
            Set<PluginMessageListenerRegistration> registrations = incomingByPlugin.get(plugin);

            if (registrations != null) {
                for (PluginMessageListenerRegistration registration : registrations) {
                    if (registration.getChannel().equals(channel)) {
                        return true;
                    }
                }
            }

            return false;
        }
    }

    @Override
    public boolean isOutgoingChannelRegistered(PluginIdentifier plugin, String channel) {
        if (plugin == null) {
            throw new IllegalArgumentException("Plugin cannot be null");
        }
        validateChannel(channel);

        synchronized (outgoingLock) {
            Set<String> channels = outgoingByPlugin.get(plugin);

            if (channels != null) {
                return channels.contains(channel);
            }

            return false;
        }
    }

    @Override
    public void dispatchIncomingMessage(Player source, String channel, byte[] message) {
        if (source == null) {
            throw new IllegalArgumentException("Player source cannot be null");
        }
        if (message == null) {
            throw new IllegalArgumentException("Message cannot be null");
        }
        validateChannel(channel);

        Set<PluginMessageListenerRegistration> registrations = getIncomingChannelRegistrations(channel);

        for (PluginMessageListenerRegistration registration : registrations) {
            registration.getListener().onPluginMessageReceived(channel, source, message);
        }
    }

    /**
     * Validates a Plugin Channel name.
     *
     * @param channel Channel name to validate.
     */
    public static void validateChannel(String channel) {
        if (channel == null) {
            throw new IllegalArgumentException("Channel cannot be null");
        }
        if (channel.length() > Messenger.MAX_CHANNEL_SIZE) {
            throw new ChannelNameTooLongException(channel);
        }
    }

    /**
     * Validates the input of a Plugin Message, ensuring the arguments are all
     * valid.
     *
     * @param messenger Messenger to use for validation.
     * @param source Source plugin of the Message.
     * @param channel Plugin Channel to send the message by.
     * @param message Raw message payload to send.
     * @throws IllegalArgumentException Thrown if the source plugin is
     *     disabled.
     * @throws IllegalArgumentException Thrown if source, channel or message
     *     is null.
     * @throws MessageTooLargeException Thrown if the message is too big.
     * @throws ChannelNameTooLongException Thrown if the channel name is too
     *     long.
     * @throws ChannelNotRegisteredException Thrown if the channel is not
     *     registered for this plugin.
     */
    public static void validatePluginMessage(Messenger messenger, PluginIdentifier source, String channel, byte[] message) {
        if (messenger == null) {
            throw new IllegalArgumentException("Messenger cannot be null");
        }
        if (source == null) {
            throw new IllegalArgumentException("Plugin source cannot be null");
        }
        if (message == null) {
            throw new IllegalArgumentException("Message cannot be null");
        }
        if (!messenger.isOutgoingChannelRegistered(source, channel)) {
            throw new ChannelNotRegisteredException(channel);
        }
        if (message.length > Messenger.MAX_MESSAGE_SIZE) {
            throw new MessageTooLargeException(message);
        }
        validateChannel(channel);
    }
}
