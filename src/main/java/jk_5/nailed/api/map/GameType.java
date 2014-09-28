package jk_5.nailed.api.map;

import javax.annotation.Nonnull;

/**
 * No description given
 *
 * @author jk-5
 */
public interface GameType {

    @Nonnull String getName();

    void onGameStarted(@Nonnull Map map);
    void onGameEnded(@Nonnull Map map);
}
