package jk_5.nailed.api.map;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import io.netty.util.concurrent.Future;

import jk_5.nailed.api.mappack.Mappack;

/**
 * No description given
 *
 * @author jk-5
 */
public interface MapLoader {

    boolean setLobbyMappack(@Nonnull Mappack mappack);
    @Nonnull Mappack getLobbyMappack();
    @Nonnull Map getLobby();
    @Nullable Map getMap(int mapid);
    @Nonnull Map getOrCreateMap(int mapid);
    @Nonnull Future<Map> createMapFor(@Nonnull Mappack mappack);
}
