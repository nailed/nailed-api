package jk_5.nailed.api.event.player;

import jk_5.nailed.api.player.Player;
import jk_5.nailed.api.world.World;

/**
 * No description given
 *
 * @author jk-5
 */
public class PlayerJoinWorldEvent extends PlayerEvent {

    private final World world;

    public PlayerJoinWorldEvent(Player player, World world) {
        super(player);
        this.world = world;
    }

    public World getWorld() {
        return world;
    }
}
