package jk_5.nailed.api.map.stat;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nonnull;

import jk_5.nailed.api.util.Checks;

/**
 * No description given
 *
 * @author jk-5
 */
public final class SubscribedStat extends Stat {

    private final String track;
    private final Map<String, String> attributes;

    public SubscribedStat(@Nonnull String name, @Nonnull String track) {
        this(name, track, new HashMap<String, String>());
    }

    public SubscribedStat(@Nonnull String name, @Nonnull String track, @Nonnull Map<String, String> attributes) {
        super(name);
        Checks.notNull(track, "track");
        Checks.notNull(attributes, "attributes");
        this.track = track;
        this.attributes = attributes;
    }

    public void onEvent(@Nonnull StatEvent event){
        if(!event.getName().equals(track)) return;
        if(this.state == event.getState()) return;
        this.state = event.getState();
        if(this.state){
            for(StatListener listener : getListeners()){
                listener.onEnable();
            }
        }else{
            for(StatListener listener : getListeners()){
                listener.onDisable();
            }
        }
    }

    public String getTrack() {
        return track;
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }
}
