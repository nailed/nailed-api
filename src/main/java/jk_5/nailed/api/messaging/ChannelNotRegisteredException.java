package jk_5.nailed.api.messaging;

/**
 * No description given
 *
 * @author jk-5
 */
public class ChannelNotRegisteredException extends RuntimeException {

    private final String channel;

    public ChannelNotRegisteredException() {
        this(null);
    }

    public ChannelNotRegisteredException(String channel) {
        super("Attempted to send a plugin message through an unregistered channel" + (channel != null ? ": " + channel : ""));
        this.channel = channel;
    }

    public String getChannel() {
        return channel;
    }
}
