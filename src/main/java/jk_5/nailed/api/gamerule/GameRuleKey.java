package jk_5.nailed.api.gamerule;

import javax.annotation.Nonnull;

/**
 * No description given
 *
 * @author jk-5
 */
public interface GameRuleKey<T> {

    public static final GameRuleKey DO_FIRE_TICK = new DefaultGameRuleKey<Boolean>("doFireTick", Type.BOOL, true);
    public static final GameRuleKey MOB_GRIEFING = new DefaultGameRuleKey<Boolean>("mobGriefing", Type.BOOL, true);
    public static final GameRuleKey KEEP_INVENTORY = new DefaultGameRuleKey<Boolean>("keepInventory", Type.BOOL, false);
    public static final GameRuleKey DO_MOB_SPAWNING = new DefaultGameRuleKey<Boolean>("doMobSpawning", Type.BOOL, true);
    public static final GameRuleKey DO_MOB_LOOT = new DefaultGameRuleKey<Boolean>("doMobLoot", Type.BOOL, true);
    public static final GameRuleKey DO_TILE_DROPS = new DefaultGameRuleKey<Boolean>("doTileDrops", Type.BOOL, true);
    public static final GameRuleKey COMMAND_BLOCK_OUTPUT = new DefaultGameRuleKey<Boolean>("commandBlockOutput", Type.BOOL, true);
    public static final GameRuleKey NATURAL_REGENERATION = new DefaultGameRuleKey<Boolean>("naturalRegeneration", Type.BOOL, true);
    public static final GameRuleKey DO_DAYLIGHT_CYCLE = new DefaultGameRuleKey<Boolean>("doDaylightCycle", Type.BOOL, true);
    public static final GameRuleKey LOG_ADMIN_COMMANDS = new DefaultGameRuleKey<Boolean>("logAdminCommands", Type.BOOL, true);
    public static final GameRuleKey SHOW_DEATH_MESSAGES = new DefaultGameRuleKey<Boolean>("showDeathMessages", Type.BOOL, true);
    public static final GameRuleKey RANDOM_TICK_SPEED = new DefaultGameRuleKey<Integer>("randomTickSpeed", Type.INTEGER, 3);
    public static final GameRuleKey SEND_COMMAND_FEEDBACK = new DefaultGameRuleKey<Boolean>("sendCommandFeedback", Type.BOOL, true);
    public static final GameRuleKey REDUCED_DEBUG_INFO = new DefaultGameRuleKey<Boolean>("reducedDebugInfo", Type.BOOL, false);

    @Nonnull String getName();
    @Nonnull Type getType();
    @Nonnull T getDefaultValue();

    public enum Type {
        BOOL,
        INTEGER
    }
}
