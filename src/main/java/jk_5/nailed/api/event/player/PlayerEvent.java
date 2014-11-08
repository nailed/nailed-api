package jk_5.nailed.api.event.player;

import jk_5.nailed.api.event.PlatformEvent;
import jk_5.nailed.api.player.Player;

/**
 * No description given
 *
 * @author jk-5
 */
public class PlayerEvent extends PlatformEvent {

    private final Player player;

    public PlayerEvent(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }
}
