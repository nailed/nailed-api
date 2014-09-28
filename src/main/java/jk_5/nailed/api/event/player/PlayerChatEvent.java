package jk_5.nailed.api.event.player;

import jk_5.eventbus.Event;
import jk_5.nailed.api.player.Player;

/**
 * No description given
 *
 * @author jk-5
 */
@Event.Cancelable
public class PlayerChatEvent extends PlayerEvent {

    private String message;

    public PlayerChatEvent(Player player, String message) {
        super(player);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
