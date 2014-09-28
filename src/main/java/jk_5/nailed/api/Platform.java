package jk_5.nailed.api;

import jk_5.nailed.api.plugin.PluginManager;

/**
 * The core accessor of the API. The implementation uses this to pass
 * constructed objects.
 *
 * @author jk-5
 */
public interface Platform {

    /**
     * Gets the API version.
     *
     * @return The API version
     */
    String getAPIVersion();

    /**
     * Gets the implementation version.
     *
     * @return The implementation version
     */
    String getImplementationVersion();

    /**
     * Gets the implementation name.
     *
     * @return The implementation name
     */
    String getImplementationName();

    PluginManager getPluginManager();
}
