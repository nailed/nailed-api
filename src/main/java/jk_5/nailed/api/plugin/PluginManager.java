package jk_5.nailed.api.plugin;

import java.util.Collection;
import javax.annotation.Nullable;

import org.slf4j.Logger;

public interface PluginManager {

    /**
     * Retrieves a {@link PluginContainer} based on its ID.
     *
     * @param id The plugin ID
     * @return The plugin or null if not found
     */
    @Nullable
    PluginContainer getPlugin(String id);

    /**
     * Gets the {@link Logger} for the {@link PluginContainer}.
     *
     * @param plugin The plugin
     * @return The logger
     */
    Logger getLogger(PluginContainer plugin);

    /**
     * Gets a {@link Collection} of all {@link PluginContainer}s
     *
     * @return The plugins
     */
    Collection<PluginContainer> getPlugins();

}
