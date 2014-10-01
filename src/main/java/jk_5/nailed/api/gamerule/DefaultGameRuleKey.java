package jk_5.nailed.api.gamerule;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nonnull;

import jk_5.nailed.api.util.Checks;

/**
 * No description given
 *
 * @author jk-5
 */
public final class DefaultGameRuleKey<T> implements GameRuleKey<T> {

    private static final Map<String, GameRuleKey<?>> names = new HashMap<String, GameRuleKey<?>>();

    static {
        //Force it to be loaded
        Class<?> cl = GameRuleKey.class;
        GameRuleKey.COMMAND_BLOCK_OUTPUT.getName();
    }

    @Nonnull
    private final String name;

    @Nonnull
    private final Type type;

    @Nonnull
    private final T defaultValue;

    DefaultGameRuleKey(@Nonnull String name, @Nonnull Type type, @Nonnull T defaultValue) {
        Checks.notNull(name, "name");
        Checks.notNull(type, "type");

        this.name = name;
        this.type = type;
        this.defaultValue = defaultValue;

        names.put(name, this);
    }

    @Override
    @Nonnull
    public String getName() {
        return name;
    }

    @Override
    @Nonnull
    public Type getType() {
        return type;
    }

    @Override
    @Nonnull
    public T getDefaultValue() {
        return defaultValue;
    }

    @Override
    public String toString() {
        return "DefaultGameRuleKey{" +
                "name='" + name + '\'' +
                ", type=" + type +
                ", defaultValue=" + defaultValue +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if(this == o){
            return true;
        }
        if(o == null || getClass() != o.getClass()){
            return false;
        }

        DefaultGameRuleKey that = (DefaultGameRuleKey) o;

        if(!defaultValue.equals(that.defaultValue)){
            return false;
        }
        if(!name.equals(that.name)){
            return false;
        }
        if(type != that.type){
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + type.hashCode();
        result = 31 * result + defaultValue.hashCode();
        return result;
    }

    public static GameRuleKey<?> getByName(String name){
        return names.get(name);
    }

    public static Collection<GameRuleKey<?>> list(){
        return names.values();
    }
}
