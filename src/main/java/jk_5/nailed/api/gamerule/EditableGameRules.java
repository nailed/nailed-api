package jk_5.nailed.api.gamerule;

import javax.annotation.Nonnull;

/**
 * No description given
 *
 * @author jk-5
 */
public interface EditableGameRules extends GameRules<EditableGameRule<?>> {

    @Nonnull
    @Override
    <T> EditableGameRule<T> get(@Nonnull GameRuleKey<T> key);
}
