package jk_5.nailed.api.gamerule;

import javax.annotation.Nonnull;

/**
 * No description given
 *
 * @author jk-5
 */
public interface GameRule<T> {

    @Nonnull GameRuleKey<T> getKey();
    @Nonnull T getValue();
}
