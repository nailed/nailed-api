package jk_5.nailed.api.mappack.metadata.impl;

import javax.annotation.Nonnull;

import jk_5.nailed.api.gamerule.DefaultGameRules;
import jk_5.nailed.api.gamerule.GameRules;
import jk_5.nailed.api.mappack.metadata.MappackWorld;
import jk_5.nailed.api.util.Location;
import jk_5.nailed.api.world.Difficulty;
import jk_5.nailed.api.world.Dimension;

/**
 * No description given
 *
 * @author jk-5
 */
public final class DefaultMappackWorld implements MappackWorld {

    public static final DefaultMappackWorld INSTANCE = new DefaultMappackWorld();

    public DefaultMappackWorld() {
    }

    @Nonnull
    @Override
    public String name() {
        return null;
    }

    @Nonnull
    @Override
    public String generator() {
        return "null";
    }

    @Nonnull
    @Override
    public Dimension dimension() {
        return Dimension.OVERWORLD;
    }

    @Nonnull
    @Override
    public Location spawnPoint() {
        return new Location(0, 64, 0);
    }

    @Nonnull
    @Override
    public GameRules gameRules() {
        return DefaultGameRules.INSTANCE;
    }

    @Nonnull
    @Override
    public String resourcePackUrl() {
        return "";
    }

    @Nonnull
    @Override
    public Difficulty difficulty() {
        return Difficulty.PEACEFUL;
    }

    @Override
    public boolean disableFood() {
        return true;
    }

    @Override
    public boolean disableDamage() {
        return true;
    }

    @Override
    public boolean disableBlockBreaking() {
        return false;
    }

    @Override
    public boolean disableBlockPlacement() {
        return false;
    }
}
