package jk_5.nailed.api.entity;

import javax.annotation.Nonnull;

import jk_5.nailed.api.math.Vector3f;

/**
 * An entity that can attain and hold a velocity.
 *
 * @author jk-5
 */
public interface Movable {
    
    /**
     * Gets the velocity value.
     * 
     * @return The velocity value
     */
    @Nonnull
    Vector3f getVelocity();

    /**
     * Sets the velocity value.
     * 
     * @param velocity The velocity value
     */
    void setVelocity(@Nonnull Vector3f velocity);
}
