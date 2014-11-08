package jk_5.nailed.api.scoreboard;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * No description given
 *
 * @author jk-5
 */
public interface ScoreboardManager {

    @Nonnull Objective getOrCreateObjective(@Nonnull String id);
    @Nullable Objective getObjective(@Nonnull String id);

    /**
     * Displays the given objective at the given location
     * Pass null as the objective to clear the display at that slot
     *
     * @param display   The slot to display the objective
     * @param objective The objective to display
     */
    void setDisplay(@Nonnull DisplayType display, @Nullable Objective objective);

    @Nonnull ScoreboardTeam getOrCreateTeam(@Nonnull String id);
    @Nullable ScoreboardTeam getTeam(@Nonnull String id);
}
