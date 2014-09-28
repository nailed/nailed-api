package jk_5.nailed.api.gamerule;

import javax.annotation.Nonnull;

import jk_5.nailed.api.util.Checks;

/**
 * No description given
 *
 * @author jk-5
 */
public class DefaultGameRule<T> implements GameRule<T> {

    private final GameRuleKey<T> key;
    private final T value;

    public DefaultGameRule(@Nonnull GameRuleKey<T> key) {
        Checks.notNull(key, "key");
        this.key = key;
        this.value = this.key.getDefaultValue();
    }

    public DefaultGameRule(@Nonnull GameRuleKey<T> key, @Nonnull T value) {
        Checks.notNull(key, "key");
        Checks.notNull(value, "value");
        this.key = key;
        this.value = value;
    }

    @Nonnull
    @Override
    public GameRuleKey<T> getKey() {
        return this.key;
    }

    @Nonnull
    @Override
    public T getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return "DefaultGameRule{" +
                "key=" + key +
                ", value=" + value +
                '}';
    }
}
