package jk_5.nailed.api.map.stat;

import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.Nonnull;

import jk_5.nailed.api.util.Checks;

/**
 * No description given
 *
 * @author jk-5
 */
public abstract class Stat {

    private final String name;
    private final Set<StatListener> listeners = new LinkedHashSet<StatListener>();
    protected boolean state = false;

    Stat(@Nonnull String name) {
        Checks.notNull(name, "name");
        this.name = name;
    }

    @Nonnull
    public String getName() {
        return name;
    }

    public void addListener(@Nonnull StatListener listener){
        Checks.notNull(listener, "listener");
        listeners.add(listener);
    }

    public void removeListener(@Nonnull StatListener listener){
        Checks.notNull(listener, "listener");
        listeners.remove(listener);
    }

    public boolean isEnabled() {
        return state;
    }

    public boolean isDisabled() {
        return !state;
    }

    protected Set<StatListener> getListeners() {
        return listeners;
    }
}
