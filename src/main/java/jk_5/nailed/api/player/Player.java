package jk_5.nailed.api.player;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import jk_5.nailed.api.GameMode;
import jk_5.nailed.api.chat.BaseComponent;
import jk_5.nailed.api.command.sender.CommandSender;
import jk_5.nailed.api.command.sender.MapCommandSender;
import jk_5.nailed.api.command.sender.WorldCommandSender;
import jk_5.nailed.api.entity.Damageable;
import jk_5.nailed.api.entity.Feedable;
import jk_5.nailed.api.entity.Flammable;
import jk_5.nailed.api.entity.HealthScalable;
import jk_5.nailed.api.entity.LevelProgressable;
import jk_5.nailed.api.entity.Movable;
import jk_5.nailed.api.entity.Positionable;
import jk_5.nailed.api.entity.PotionAffectable;
import jk_5.nailed.api.entity.Rotatable;
import jk_5.nailed.api.map.GameWinnable;
import jk_5.nailed.api.map.Map;
import jk_5.nailed.api.messaging.PluginMessageRecipient;
import jk_5.nailed.api.scoreboard.ScoreboardManager;
import jk_5.nailed.api.util.Identifiable;
import jk_5.nailed.api.util.TitleMessage;
import jk_5.nailed.api.world.World;

/**
 * No description given
 *
 * @author jk-5
 */
public interface Player extends Identifiable, Damageable, Feedable, Flammable, HealthScalable, LevelProgressable, Movable, Positionable, Rotatable, PotionAffectable, CommandSender, PluginMessageRecipient, WorldCommandSender, MapCommandSender, GameWinnable {

    /**
     * Gets the players last known username
     *
     * @return The player's last known username
     */
    String getName();

    /**
     * Gets the player's display name. If none set, returns their current username.
     *
     * @return The player's display name
     */
    String getDisplayName();

    GameMode getGameMode();

    void setGameMode(GameMode gameMode);

    void kick(String reason);

    ScoreboardManager getScoreboardManager();

    void loadResourcePack(String url, String hash);
    BaseComponent getDescriptionComponent();

    World getWorld();

    boolean isOnline();

    void teleportTo(World world);

    void setAllowedToFly(boolean isAllowedToFly);

    @Nullable
    Map getMap();

    void displayTitle(@Nonnull TitleMessage title);
    void clearTitle();

    void displaySubtitle(BaseComponent... message);
    void setSubtitle(BaseComponent... message);
    void clearSubtitle();

    void clearInventory();
}
