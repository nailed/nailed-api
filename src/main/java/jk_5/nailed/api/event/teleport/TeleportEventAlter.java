package jk_5.nailed.api.event.teleport;

import jk_5.nailed.api.player.Player;
import jk_5.nailed.api.util.Location;
import jk_5.nailed.api.util.TeleportOptions;
import jk_5.nailed.api.world.World;

/**
 * No description given
 *
 * @author jk-5
 */
public class TeleportEventAlter extends TeleportEvent {

    private Location location = null;

    public TeleportEventAlter(World oldWorld, World newWorld, Player player, TeleportOptions options) {
        super(oldWorld, newWorld, player, options);
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
