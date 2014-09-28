package jk_5.nailed.api;

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
}
