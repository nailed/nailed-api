package jk_5.nailed.api.world;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import jk_5.nailed.api.mappack.metadata.MappackWorld;

/**
 * No description given
 *
 * @author jk-5
 */
public class WorldContext {

    private final String name;
    private final String subName;
    private final MappackWorld config;

    public WorldContext(@Nonnull String name, @Nonnull String subName) {
        this(name, subName, null);
    }

    public WorldContext(@Nonnull String name, @Nonnull String subName, @Nullable MappackWorld config) {
        this.name = name;
        this.subName = subName;
        this.config = config;
    }

    @Nonnull
    public String getName() {
        return name;
    }

    @Nonnull
    public String getSubName() {
        return subName;
    }

    @Nullable
    public MappackWorld getConfig() {
        return config;
    }
}
