package jk_5.nailed.api.entity;

import javax.annotation.Nonnull;

import jk_5.nailed.api.math.Vector3d;

/**
 * An entity that has a position.
 *
 * @author jk-5
 */
public interface Positionable {

    /**
     * Gets the position.
     * 
     * @return position The position
     */
    @Nonnull
    Vector3d getPosition();

    /**
     * Sets the position.
     * 
     * @param position The position to set to
     */
    void setPosition(@Nonnull Vector3d position);

}
