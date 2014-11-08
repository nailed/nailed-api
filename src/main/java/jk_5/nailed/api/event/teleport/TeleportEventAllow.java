package jk_5.nailed.api.event.teleport;

import jk_5.eventbus.Event;
import jk_5.nailed.api.player.Player;
import jk_5.nailed.api.util.TeleportOptions;
import jk_5.nailed.api.world.World;

/**
 * No description given
 *
 * @author jk-5
 */
@Event.Cancelable
public class TeleportEventAllow extends TeleportEvent {

    public TeleportEventAllow(World oldWorld, World newWorld, Player player, TeleportOptions options) {
        super(oldWorld, newWorld, player, options);
    }
}
