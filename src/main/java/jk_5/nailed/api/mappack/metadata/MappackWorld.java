package jk_5.nailed.api.mappack.metadata;

import javax.annotation.Nonnull;

import jk_5.nailed.api.gamerule.GameRules;
import jk_5.nailed.api.util.Location;
import jk_5.nailed.api.world.Difficulty;
import jk_5.nailed.api.world.Dimension;

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
    @Nonnull String resourcePackUrl();
    @Nonnull Difficulty difficulty();

    boolean disableFood();
    boolean disableDamage();
    boolean disableBlockBreaking();
    boolean disableBlockPlacement();
}
