package jk_5.nailed.api.plugin;

import java.util.Collection;
import javax.annotation.Nullable;

public interface PluginManager {

    /**
     * Retrieves a {@link PluginContainer} based on an object that can identify the plugin
     *
     * Possible options:
     * <ul>
     *     <li>The Plugin id</li>
     *     <li>The Plugin class (the class that has the {@link Plugin} annotation</li>
     *     <li>The {@link PluginIdentifier} of the plugin</li>
     * </ul>
     *
     * @param identifier The plugin identifier
     * @return The plugin or null if not found
     */
    @Nullable
    PluginContainer getPlugin(Object identifier);

    /**
     * Gets a {@link Collection} of all {@link PluginContainer}s
     *
     * @return The plugins
     */
    Collection<PluginContainer> getPlugins();

}
