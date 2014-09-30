package jk_5.nailed.api.event.mappack;

import jk_5.nailed.api.mappack.Mappack;

/**
 * No description given
 *
 * @author jk-5
 */
public class MappackUnregisteredEvent extends MappackEvent {

    public MappackUnregisteredEvent(Mappack mappack) {
        super(mappack);
    }
}
