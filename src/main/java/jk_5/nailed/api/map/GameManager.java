package jk_5.nailed.api.map;

import javax.annotation.Nonnull;

/**
 * No description given
 *
 * @author jk-5
 */
public interface GameManager {

    GameStartResult startGame();
    boolean endGame();
    boolean isGameRunning();

    boolean hasCustomGameType();
    @Nonnull GameType getGameType();

    void setWinner(@Nonnull GameWinnable winner);

    void setWinInterrupt(boolean winInterrupt);
    boolean isWinInterrupt();
    void setUnreadyInterrupt(boolean unreadyInterrupt);
    boolean isUnreadyInterrupt();
}
