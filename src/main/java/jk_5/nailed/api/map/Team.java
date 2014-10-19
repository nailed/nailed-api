package jk_5.nailed.api.map;

import java.util.Collection;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import jk_5.nailed.api.chat.ChatColor;
import jk_5.nailed.api.player.Player;
import jk_5.nailed.api.util.Location;

/**
 * No description given
 *
 * @author jk-5
 * @see jk_5.nailed.api.mappack.metadata.MappackTeam
 */
public interface Team extends GameWinnable {

    @Nonnull String id();
    @Nonnull String name();
    @Nonnull ChatColor color();
    @Nonnull Collection<Player> members();

    @Nullable Location getSpawnPoint();
    void setSpawnPoint(@Nullable Location spawnpoint);
}
