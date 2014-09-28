package jk_5.nailed.api.util;

import java.util.UUID;

/**
 * An identifiable object has a UUID that can be retrieved.
 *
 * @author jk-5
 */
public interface Identifiable {

    /**
     * Gets the unique ID for this object.
     *
     * @return The {@link UUID}
     */
    UUID getUniqueId();

}
