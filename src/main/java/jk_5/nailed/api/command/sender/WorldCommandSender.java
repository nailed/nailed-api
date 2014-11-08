package jk_5.nailed.api.command.sender;

import jk_5.nailed.api.world.World;

/**
 * No description given
 *
 * @author jk-5
 */
public interface WorldCommandSender extends CommandSender, MapCommandSender {

    World getWorld();
}
