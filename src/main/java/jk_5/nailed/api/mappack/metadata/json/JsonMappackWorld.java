package jk_5.nailed.api.mappack.metadata.json;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.google.gson.JsonObject;

import jk_5.nailed.api.gamerule.GameRule;
import jk_5.nailed.api.gamerule.GameRules;
import jk_5.nailed.api.gamerule.ImmutableJsonGameRules;
import jk_5.nailed.api.mappack.MappackConfigurationException;
import jk_5.nailed.api.mappack.metadata.MappackWorld;
import jk_5.nailed.api.mappack.metadata.impl.DefaultMappackWorld;
import jk_5.nailed.api.util.Checks;
import jk_5.nailed.api.util.Location;
import jk_5.nailed.api.world.Difficulty;
import jk_5.nailed.api.world.Dimension;

/**
 * No description given
 *
 * @author jk-5
 */
public class JsonMappackWorld implements MappackWorld {

    private final MappackWorld parent;
    private final String name;
    private final String generator;
    private final Dimension dimension;
    private final Location spawnPoint;
    private final GameRules<GameRule<?>> gameRules;
    private final String resourcePackUrl;
    private final Difficulty difficulty;
    private final boolean disableFood;
    private final boolean disableDamage;
    private final boolean disableBlockBreaking;
    private final boolean disableBlockPlacement;

    public JsonMappackWorld(@Nullable String name, @Nonnull JsonObject json) throws MappackConfigurationException {
        this(name, json, DefaultMappackWorld.INSTANCE);
    }

    public JsonMappackWorld(@Nullable String name, @Nonnull JsonObject json, @Nonnull MappackWorld parent) throws MappackConfigurationException {
        Checks.notNull(parent, "parent");

        this.name = name;
        this.parent = parent;

        this.generator = (json.has("generator") ? json.get("generator").getAsString() : parent.generator());
        this.dimension = (json.has("dimension") ? Dimension.parse(json.get("generator").getAsString()) : parent.dimension());
        this.spawnPoint = (json.has("spawnpoint") ? Location.read(json.getAsJsonObject("spawnpoint")) : parent.spawnPoint());
        this.gameRules = new ImmutableJsonGameRules(json.has("gamerules") ? json.getAsJsonObject("gamerules") : new JsonObject(), parent.gameRules());
        this.resourcePackUrl = (json.has("resourcepack") ? json.get("resourcepack").getAsString() : parent.resourcePackUrl());
        this.difficulty = (json.has("difficulty") ? Difficulty.byName(json.get("difficulty").getAsString()) : parent.difficulty());
        this.disableFood = (json.has("disableFood") ? json.get("disableFood").getAsBoolean() : parent.disableFood());
        this.disableDamage = (json.has("disableDamage") ? json.get("disableDamage").getAsBoolean() : parent.disableDamage());
        this.disableBlockBreaking = (json.has("disableBlockBreaking") ? json.get("disableBlockBreaking").getAsBoolean() : parent.disableBlockBreaking());
        this.disableBlockPlacement = (json.has("disableBlockPlacement") ? json.get("disableBlockPlacement").getAsBoolean() : parent.disableBlockPlacement());
    }

    @Nonnull
    @Override
    public String name() {
        return name;
    }

    @Nonnull
    @Override
    public String generator() {
        return generator;
    }

    @Nonnull
    @Override
    public Dimension dimension() {
        return dimension;
    }

    @Nonnull
    @Override
    public Location spawnPoint() {
        return spawnPoint;
    }

    @Nonnull
    @Override
    public GameRules gameRules() {
        return gameRules;
    }

    @Nonnull
    @Override
    public String resourcePackUrl() {
        return resourcePackUrl;
    }

    @Nonnull
    @Override
    public Difficulty difficulty() {
        return difficulty;
    }

    @Override
    public boolean disableFood() {
        return disableFood;
    }

    @Override
    public boolean disableDamage() {
        return disableDamage;
    }

    @Override
    public boolean disableBlockBreaking() {
        return disableBlockBreaking;
    }

    @Override
    public boolean disableBlockPlacement() {
        return disableBlockPlacement;
    }
}
