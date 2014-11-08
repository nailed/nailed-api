package jk_5.nailed.api.gamerule;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nonnull;

/**
 * No description given
 *
 * @author jk-5
 */
public final class DefaultGameRules implements GameRules<GameRule<?>> {

    public static final DefaultGameRules INSTANCE = new DefaultGameRules();
    private final Map<GameRuleKey<?>, GameRule<?>> gameRuleMap = new HashMap<GameRuleKey<?>, GameRule<?>>();

    private DefaultGameRules() {
        for(GameRuleKey<?> key : DefaultGameRuleKey.list()){
            //noinspection unchecked
            gameRuleMap.put(key, new DefaultGameRule(key));
        }
    }

    @Nonnull
    @Override
    public <T> GameRule<T> get(@Nonnull GameRuleKey<T> key) {
        //noinspection unchecked
        return (GameRule<T>) gameRuleMap.get(key);
    }

    @Nonnull
    @Override
    public Collection<GameRule<?>> list() {
        return Collections.unmodifiableCollection(gameRuleMap.values());
    }

    @Override
    public String toString() {
        return "DefaultGameRules{" +
                "gameRuleMap=" + gameRuleMap +
                '}';
    }
}
