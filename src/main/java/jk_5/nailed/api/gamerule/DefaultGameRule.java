package jk_5.nailed.api.gamerule;

import javax.annotation.Nonnull;

import jk_5.nailed.api.util.Checks;

/**
 * No description given
 *
 * @author jk-5
 */
class DefaultGameRule<T> implements GameRule<T> {

    private final GameRuleKey<T> key;

    public DefaultGameRule(@Nonnull GameRuleKey<T> key) {
        Checks.notNull(key, "key");
        this.key = key;
    }

    @Nonnull
    @Override
    public GameRuleKey<T> getKey() {
        return this.key;
    }

    @Nonnull
    @Override
    public T getValue() {
        return this.key.getDefaultValue();
    }

    @Override
    public String toString() {
        return "DefaultGameRule{" +
                "key=" + key +
                ", value=" + getValue() +
                '}';
    }
}
