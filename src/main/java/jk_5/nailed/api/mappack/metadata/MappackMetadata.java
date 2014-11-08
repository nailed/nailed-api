package jk_5.nailed.api.mappack.metadata;

import javax.annotation.Nonnull;

/**
 * No description given
 *
 * @author jk-5
 */
public interface MappackMetadata {

    @Nonnull String name();
    @Nonnull String version();
    @Nonnull MappackAuthor[] authors();
    @Nonnull MappackWorld[] worlds();
    @Nonnull MappackTeam[] teams();
    @Nonnull Tutorial tutorial();
    @Nonnull String gameType();
    @Nonnull StatConfig[] stats();
}
