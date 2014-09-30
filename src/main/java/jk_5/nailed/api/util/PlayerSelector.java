package jk_5.nailed.api.util;

import jk_5.nailed.api.player.Player;

/**
 * No description given
 *
 * @author jk-5
 */
public interface PlayerSelector {

    Player[] matchPlayers(String pattern, Location base);
    boolean matchesMultiplePlayers(String pattern);
}
