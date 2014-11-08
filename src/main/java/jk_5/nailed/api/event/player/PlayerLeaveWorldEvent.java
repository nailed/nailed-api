package jk_5.nailed.api.event.player;

import jk_5.nailed.api.player.Player;
import jk_5.nailed.api.world.World;

/**
 * No description given
 *
 * @author jk-5
 */
public class PlayerLeaveWorldEvent extends PlayerEvent {

    private final World world;

    public PlayerLeaveWorldEvent(Player player, World world) {
        super(player);
        this.world = world;
    }

    public World getWorld() {
        return world;
    }
}
