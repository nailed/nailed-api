package jk_5.nailed.api.messaging;

/**
 * No description given
 *
 * @author jk-5
 */
public class ReservedChannelException extends RuntimeException {

    private final String channel;

    public ReservedChannelException() {
        this(null);
    }

    public ReservedChannelException(String channel) {
        super("Attempted to register for a reserved channel name" + (channel != null ? ": " + channel : ""));
        this.channel = channel;
    }

    public String getChannel() {
        return channel;
    }
}
