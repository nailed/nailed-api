package jk_5.nailed.api.event;

import javax.annotation.Nonnull;

import jk_5.nailed.api.Platform;

/**
 * An event that deals with the platform.
 *
 * @see Platform
 * @author jk-5
 */
public interface PlatformEvent {

    /**
     * Get the platform.
     *
     * @return The platform
     */
    @Nonnull
    Platform getPlatform();
}
