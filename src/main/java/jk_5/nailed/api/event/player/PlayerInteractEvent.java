package jk_5.nailed.api.event.player;

import jk_5.eventbus.Event;
import jk_5.nailed.api.player.Player;
import jk_5.nailed.api.util.Location;

/**
 * No description given
 *
 * @author jk-5
 */
@Event.Cancelable
public class PlayerInteractEvent extends PlayerEvent {

    private final Action action;
    private final Location clicked;

    public PlayerInteractEvent(Player player, Action action) {
        this(player, action, null);
    }

    public PlayerInteractEvent(Player player, Action action, Location clicked) {
        super(player);
        this.action = action;
        this.clicked = clicked;
    }

    public Action getAction() {
        return action;
    }

    public Location getClicked() {
        return clicked;
    }

    public static enum Action {
        LEFT_CLICK_AIR,
        LEFT_CLICK_BLOCK,
        RIGHT_CLICK_AIR,
        RIGHT_CLICK_BLOCK
    }
}
