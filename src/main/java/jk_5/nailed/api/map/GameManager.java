package jk_5.nailed.api.map;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * No description given
 *
 * @author jk-5
 */
public interface GameManager {

    @Nonnull GameStartResult startGame();
    boolean endGame();
    boolean isGameRunning();

    boolean hasCustomGameType();
    @Nullable GameType getGameType();

    void setWinner(@Nonnull GameWinnable winner);

    void setWinInterrupt(boolean winInterrupt);
    boolean isWinInterrupt();
    void setUnreadyInterrupt(boolean unreadyInterrupt);
    boolean isUnreadyInterrupt();
}
