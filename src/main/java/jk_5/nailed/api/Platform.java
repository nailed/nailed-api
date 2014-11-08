package jk_5.nailed.api;

import java.io.File;
import java.util.Collection;
import java.util.UUID;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import jk_5.nailed.api.chat.BaseComponent;
import jk_5.nailed.api.command.sender.CommandSender;
import jk_5.nailed.api.map.GameTypeRegistry;
import jk_5.nailed.api.map.MapLoader;
import jk_5.nailed.api.mappack.MappackRegistry;
import jk_5.nailed.api.messaging.Messenger;
import jk_5.nailed.api.player.Player;
import jk_5.nailed.api.plugin.PluginManager;
import jk_5.nailed.api.scheduler.Scheduler;
import jk_5.nailed.api.util.PlayerSelector;
import jk_5.nailed.api.world.DefaultWorldProviders;
import jk_5.nailed.api.world.World;
import jk_5.nailed.api.world.WorldContext;
import jk_5.nailed.api.world.WorldProvider;

/**
 * The core accessor of the API. The implementation uses this to pass
 * constructed objects.
 *
 * @author jk-5
 */
public interface Platform {

    /**
     * Gets the API version.
     *
     * @return The API version
     */
    @Nonnull
    String getAPIVersion();

    /**
     * Gets the implementation version.
     *
     * @return The implementation version
     */
    @Nonnull
    String getImplementationVersion();

    /**
     * Gets the implementation name.
     *
     * @return The implementation name
     */
    @Nonnull
    String getImplementationName();

    @Nonnull
    PluginManager getPluginManager();

    @Nonnull
    File getRuntimeDirectory();

    @Nonnull
    Messenger getMessenger();

    @Nonnull
    GameTypeRegistry getGameTypeRegistry();

    @Nullable
    Player getPlayer(UUID uuid);
    @Nullable
    @Deprecated
    Player getPlayerByName(String name);
    @Nonnull
    Collection<Player> getOnlinePlayers();

    void broadcastMessage(BaseComponent... message);

    @Nullable
    World getWorld(int dimensionId);

    @Nonnull
    DefaultWorldProviders getDefaultWorldProviders();

    @Nonnull
    World createNewWorld(WorldProvider provider, WorldContext ctx);

    @Nonnull
    Scheduler getScheduler();

    @Nonnull
    PlayerSelector getPlayerSelector();

    @Nonnull
    MappackRegistry getMappackRegistry();

    @Nonnull
    CommandSender getConsoleCommandSender();

    @Nonnull
    MapLoader getMapLoader();
}
