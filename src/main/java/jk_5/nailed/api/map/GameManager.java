package jk_5.nailed.api.map;

import javax.annotation.Nonnull;

/**
 * No description given
 *
 * @author jk-5
 */
public interface GameManager {

    boolean startGame();
    boolean endGame();
    boolean isGameRunning();

    boolean hasCustomGameType();
    @Nonnull GameType getGameType();
}
