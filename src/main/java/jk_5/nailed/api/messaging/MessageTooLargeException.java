package jk_5.nailed.api.messaging;

/**
 * No description given
 *
 * @author jk-5
 */
public class MessageTooLargeException extends RuntimeException {

    public MessageTooLargeException() {
        this("Attempted to send a plugin message that was too large. The maximum length a plugin message may be is " + Messenger.MAX_MESSAGE_SIZE + " bytes.");
    }

    public MessageTooLargeException(String message) {
        super(message);
    }

    public MessageTooLargeException(int length) {
        this("Attempted to send a plugin message that was too large. The maximum length a plugin message may be is " + Messenger.MAX_MESSAGE_SIZE + " bytes (tried to send one that is " + length + " bytes long).");
    }

    public MessageTooLargeException(byte[] message){
        this(message.length);
    }
}
