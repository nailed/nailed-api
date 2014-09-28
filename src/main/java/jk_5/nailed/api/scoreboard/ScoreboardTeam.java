package jk_5.nailed.api.scoreboard;

import java.util.Collection;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import jk_5.nailed.api.player.Player;

/**
 * No description given
 *
 * @author jk-5
 */
public interface ScoreboardTeam {

    @Nonnull String getId();
    @Nonnull String getDisplayName();
    void setDisplayName(@Nonnull String displayName);
    @Nullable String getPrefix();
    void setPrefix(@Nullable String prefix);
    @Nullable String getSuffix();
    void setSuffix(@Nullable String suffix);

    boolean isFriendlyFire();
    void setFriendlyFire(boolean friendlyFire);
    boolean areFriendlyInvisiblesInvisible();
    void setFriendlyInvisiblesInvisible(boolean friendlyInvisiblesInvisible);
    Visibility getNameTagVisibility();
    void setNameTagVisibility(Visibility nameTagVisibility);
    Visibility getDeathMessageVisibility();
    void setDeathMessageVisibility(Visibility deathMessageVisibility);

    boolean addPlayer(Player player);
    boolean removePlayer(Player player);

    Collection<Player> getPlayers();
    Collection<String> getPlayerNames();
}
