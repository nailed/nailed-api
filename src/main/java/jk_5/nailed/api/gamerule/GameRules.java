package jk_5.nailed.api.gamerule;

import java.util.Collection;
import javax.annotation.Nonnull;

/**
 * No description given
 *
 * @author jk-5
 */
public interface GameRules<V extends GameRule<?>> {

    @Nonnull <T> GameRule<T> get(@Nonnull GameRuleKey<T> key);
    @Nonnull Collection<V> list();
}
