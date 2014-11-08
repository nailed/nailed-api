package jk_5.nailed.api.mappack;

import java.util.Collection;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * This class is responsible for tracking, loading or registering mappacks
 *
 * @author jk-5
 */
public interface MappackRegistry {

    /**
     * Registers the mappack to the mappack system
     * It checks if a mappack with that id, or that exact mappack already exists.
     * If one (or both) of those exist, it returns false.
     * When it is successfully registered, it returns true.
     *
     * @param mappack the mappack to register to the system
     * @return true if the mappack was registered successfully, false otherwise, or when dupplicates were found
     */
    boolean register(@Nonnull Mappack mappack);

    /**
     * Obtains the mappack with the given name
     *
     * @param name the name to return the mappack for
     * @return Some(mappack) when a mappack with the given name was found. None otherwise
     */
    @Nullable
    Mappack getByName(@Nonnull String name);

    /**
     * Returns all the mappacks that implement or extend the given class
     *
     * @param cl the class to check for
     * @tparam T the type of the mappack class, which is the same type as will be returned
     * @return an array of all the mappacks with the given type
     */
    @Nonnull
    <T> Collection<T> getByType(@Nonnull Class<? extends T> cl);

    @Nonnull
    Collection<Mappack> getAll();

    @Nonnull
    Collection<String> getAllIds();

    boolean unregister(@Nonnull Mappack mappack);

    void reload();
}
