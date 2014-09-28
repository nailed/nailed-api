package jk_5.nailed.api.gamerule;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nonnull;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import jk_5.nailed.api.mappack.MappackConfigurationException;

/**
 * No description given
 *
 * @author jk-5
 */
public class ImmutableJsonGameRules implements GameRules<GameRule<?>> {

    private final Map<GameRuleKey<?>, GameRule<?>> gameRuleMap = new HashMap<GameRuleKey<?>, GameRule<?>>();

    public ImmutableJsonGameRules(JsonObject json) throws MappackConfigurationException {
        this(json, DefaultGameRules.INSTANCE);
    }

    public ImmutableJsonGameRules(JsonObject json, GameRules<GameRule<?>> parent) throws MappackConfigurationException {
        for(GameRule<?> r : parent.list()){
            //noinspection unchecked
            gameRuleMap.put(r.getKey(), r);
        }
        for(Map.Entry<String, JsonElement> entry : json.entrySet()){
            GameRuleKey key = DefaultGameRuleKey.getByName(entry.getKey());
            if(!entry.getValue().isJsonPrimitive()) throw new MappackConfigurationException("Mappack json object contains invalid element: " + entry.getValue().toString());
            gameRuleMap.put(key, new DefaultGameRule<Object>(key, entry.getValue()));
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
}
