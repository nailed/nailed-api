package jk_5.nailed.api.map.stat;

import javax.annotation.Nonnull;

/**
 * No description given
 *
 * @author jk-5
 */
public final class ModifiableStat extends Stat {

    public ModifiableStat(@Nonnull String name) {
        super(name);
    }

    public void enable(){
        if(isEnabled()) return;
        state = true;
        for(StatListener listener : getListeners()){
            listener.onEnable();
        }
    }

    public void disable(){
        if(isDisabled()) return;
        state = false;
        for(StatListener listener : getListeners()){
            listener.onDisable();
        }
    }
}
