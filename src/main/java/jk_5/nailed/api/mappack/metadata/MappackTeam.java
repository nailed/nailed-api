package jk_5.nailed.api.mappack.metadata;

import jk_5.nailed.api.chat.ChatColor;
import jk_5.nailed.api.scoreboard.Visibility;

/**
 * No description given
 *
 * @author jk-5
 */
public interface MappackTeam {

    String id();
    String name();
    ChatColor color();

    /**
     * @return True if you can hurt players in the same team. False otherwise
     */
    boolean isFriendlyFire();
    boolean areFriendlyInvisiblesInvisible();
    Visibility getNameTagVisibility();
    Visibility getDeathMessageVisibility();
}
