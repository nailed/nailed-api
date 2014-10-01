package jk_5.nailed.api.event.mappack;

import jk_5.nailed.api.event.PlatformEvent;
import jk_5.nailed.api.map.MapLoader;
import jk_5.nailed.api.mappack.Mappack;
import jk_5.nailed.api.mappack.MappackRegistry;

/**
 * No description given
 *
 * @author jk-5
 */
public class RegisterMappacksEvent extends PlatformEvent {

    private final MappackRegistry registry;
    private final MapLoader mapLoader;

    public RegisterMappacksEvent(MappackRegistry registry, MapLoader mapLoader) {
        this.registry = registry;
        this.mapLoader = mapLoader;
    }

    public boolean registerMappack(Mappack mappack){
        return registry.register(mappack);
    }

    public boolean setLobbyMappack(Mappack mappack){
        return mapLoader.setLobbyMappack(mappack);
    }
}
