package jk_5.nailed.api.event.world;

import jk_5.nailed.api.event.PlatformEvent;
import jk_5.nailed.api.world.World;

/**
 * No description given
 *
 * @author jk-5
 */
public class WorldEvent extends PlatformEvent {

    private final World world;

    public WorldEvent(World world) {
        this.world = world;
    }

    public World getWorld() {
        return world;
    }
}
