package jk_5.nailed.api.plugin;

/**
 * A wrapper around a class marked with an {@link Plugin} annotation to retrieve
 * information from the annotation for easier use.
 *
 * @author jk-5
 */
public interface PluginContainer {

    /**
     * Gets the id of the {@link Plugin} within this container.
     *
     * @return The id
     */
    String getId();

    /**
     * Gets the name of the {@link Plugin} within this container.
     *
     * @return The name
     */
    String getName();

    /**
     * Gets the version of the {@link Plugin} within this container.
     *
     * @return The name
     */
    String getVersion();

    /**
     * Returns the created instance of {@link Plugin}.
     *
     * @return The instance
     */
    Object getInstance();

}
