package jk_5.nailed.api.messaging;

/**
 * No description given
 *
 * @author jk-5
 */
public class ChannelNameTooLongException extends RuntimeException {

    private final String channel;

    public ChannelNameTooLongException() {
        this(null);
    }

    public ChannelNameTooLongException(String channel) {
        super("Attempted to send a Plugin Message to a channel that was too large. The maximum length a channel may be is " + Messenger.MAX_CHANNEL_SIZE + " chars" + (channel == null ? "." : " attempted " + channel.length() + " - '" + channel + "."));
        this.channel = channel;
    }

    public String getChannel() {
        return channel;
    }
}
