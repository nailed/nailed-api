package jk_5.nailed.api.map;

import io.netty.util.concurrent.Future;
import jk_5.nailed.api.mappack.Mappack;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * No description given
 *
 * @author jk-5
 */
public interface MapLoader {

    @Nonnull Mappack getLobbyMappack();
    @Nonnull Map getLobby();
    @Nullable Map getMap(int mapid);
    @Nonnull Future<Map> createMapFor(@Nonnull Mappack mappack);
}
