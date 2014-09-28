package jk_5.nailed.api.scoreboard;

import javax.annotation.Nonnull;

/**
 * No description given
 *
 * @author jk-5
 */
public interface Score {

    @Nonnull String getName();
    int getValue();
    void setValue(int value);
    void addValue(int value);
    void update();
}
