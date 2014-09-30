package jk_5.nailed.api.event.teleport;

import jk_5.nailed.api.event.PlatformEvent;
import jk_5.nailed.api.player.Player;
import jk_5.nailed.api.util.TeleportOptions;
import jk_5.nailed.api.world.World;

/**
 * No description given
 *
 * @author jk-5
 */
public class TeleportEvent extends PlatformEvent {

    private final World oldWorld;
    private final World newWorld;
    private final Player player;
    private final TeleportOptions options;

    public TeleportEvent(World oldWorld, World newWorld, Player player, TeleportOptions options) {
        this.oldWorld = oldWorld;
        this.newWorld = newWorld;
        this.player = player;
        this.options = options;
    }

    public World getOldWorld() {
        return oldWorld;
    }

    public World getNewWorld() {
        return newWorld;
    }

    public Player getPlayer() {
        return player;
    }

    public TeleportOptions getOptions() {
        return options;
    }
}
