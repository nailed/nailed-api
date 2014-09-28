package jk_5.nailed.api.map.stat;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import jk_5.nailed.api.util.Checks;

/**
 * No description given
 *
 * @author jk-5
 */
public class StatEvent {

    private final String name;
    private final boolean state;
    private final Map<String, String> attributes;

    public StatEvent(String name, boolean state) {
        this(name, state, new HashMap<String, String>());
    }

    public StatEvent(@Nonnull String name, boolean state, @Nonnull Map<String, String> attributes) {
        Checks.notNull(name, "name");
        Checks.notNull(attributes, "attributes");
        this.name = name;
        this.state = state;
        this.attributes = attributes;
    }

    public boolean getState() {
        return state;
    }

    @Nonnull
    public String getName() {
        return name;
    }

    @Nonnull
    public Map<String, String> getAttributes() {
        return attributes;
    }

    public void addAttribute(@Nonnull String name, @Nonnull String value){
        attributes.put(name, value);
    }

    @Nullable
    public String getAttribute(String name){
        return attributes.get(name);
    }
}
