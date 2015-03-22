package jk_5.nailed.api.mappack.metadata;

import jk_5.nailed.api.gamerule.GameRules;
import jk_5.nailed.api.util.Location;
import jk_5.nailed.api.world.Difficulty;
import jk_5.nailed.api.world.Dimension;

import javax.annotation.Nonnull;

/**
 * No description given
 *
 * @author jk-5
 */
public interface MappackWorld {

    @Nonnull String name();
    @Nonnull String generator();
    @Nonnull Dimension dimension();
    @Nonnull Location spawnPoint();
    @Nonnull GameRules gameRules();
    @Nonnull String resourcePackUrl(); //TODO: should this be nullable?
    @Nonnull Difficulty difficulty();
    boolean isDefault();

    boolean disableFood();
    boolean disableDamage();
    boolean disableBlockBreaking();
    boolean disableBlockPlacement();
}
