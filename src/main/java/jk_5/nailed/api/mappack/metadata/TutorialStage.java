package jk_5.nailed.api.mappack.metadata;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import jk_5.nailed.api.util.Location;

/**
 * No description given
 *
 * @author jk-5
 */
public interface TutorialStage {

    @Nonnull String title();
    @Nonnull String[] messages();
    @Nullable Location teleport();
}
