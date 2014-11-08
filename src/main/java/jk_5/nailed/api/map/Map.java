package jk_5.nailed.api.map;

import java.util.Collection;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import jk_5.nailed.api.chat.BaseComponent;
import jk_5.nailed.api.map.stat.StatManager;
import jk_5.nailed.api.mappack.Mappack;
import jk_5.nailed.api.player.Player;
import jk_5.nailed.api.scoreboard.ScoreboardManager;
import jk_5.nailed.api.world.World;

/**
 * No description given
 *
 * @author jk-5
 */
public interface Map {

    @Deprecated
    int id();

    @Nonnull Collection<World> worlds();
    @Nonnull World[] worldsArray();
    @Nonnull World defaultWorld();
    @Nullable Mappack mappack();
    void addWorld(@Nonnull World world);

    @Nullable Team getTeam(String name);
    @Nullable Team getPlayerTeam(Player player);
    void setPlayerTeam(@Nonnull Player player, @Nullable Team team);
    @Nonnull Collection<Team> getTeams();

    void broadcastChatMessage(BaseComponent... message);

    @Nonnull Collection<Player> players();

    @Nonnull ScoreboardManager getScoreboardManager();
    @Nonnull GameManager getGameManager();
    @Nonnull StatManager getStatManager();
}
