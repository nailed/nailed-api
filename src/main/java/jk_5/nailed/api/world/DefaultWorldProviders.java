package jk_5.nailed.api.world;

import javax.annotation.Nonnull;

/**
 * No description given
 *
 * @author jk-5
 */
public interface DefaultWorldProviders {

    @Nonnull WorldProvider getVoidProvider();
    @Nonnull WorldProvider getOverworldProvider();
    @Nonnull WorldProvider getNetherProvider();
    @Nonnull WorldProvider getEndProvider();
    @Nonnull WorldProvider getFlatProvider(@Nonnull String pattern);
}
