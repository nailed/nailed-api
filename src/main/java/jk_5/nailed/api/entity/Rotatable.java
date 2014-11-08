package jk_5.nailed.api.entity;

import javax.annotation.Nonnull;

import jk_5.nailed.api.math.EulerDirection;
import jk_5.nailed.api.math.Vector3f;

/**
 * An entity that can be rotated.
 *
 * @author jk-5
 */
public interface Rotatable {

    /**
     * Gets the rotation as a vector.
     * This does not support the roll component of the entity's rotation.
     *
     * @return rotation A possibly, but not necessarily, unit vector
     */
    @Nonnull
    Vector3f getVectorRotation();

    /**
     * Sets the rotation to a vector.
     * This does not support the roll component of the entity's rotation,
     * any previous roll value will be removed.
     *
     * @param rotation The rotation to set the entity to
     */
    void setVectorRotation(@Nonnull Vector3f rotation);

    /**
     * Gets the rotation as a EulerDirection.
     *
     * @return rotation The rotation as a EulerDirection
     */
    @Nonnull
    EulerDirection getRotation();

    /**
     * Sets the rotation.
     *
     * @param rotation The rotation to set the entity to
     */
    void setRotation(@Nonnull EulerDirection rotation);
}
