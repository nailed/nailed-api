package jk_5.nailed.api.event.mappack;

import jk_5.nailed.api.event.PlatformEvent;
import jk_5.nailed.api.mappack.Mappack;

/**
 * No description given
 *
 * @author jk-5
 */
public class MappackEvent extends PlatformEvent {

    private final Mappack mappack;

    public MappackEvent(Mappack mappack) {
        this.mappack = mappack;
    }

    public Mappack getMappack() {
        return mappack;
    }
}
