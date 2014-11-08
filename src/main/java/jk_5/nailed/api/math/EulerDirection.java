package jk_5.nailed.api.math;

import java.io.Serializable;

/**
 * Represents a euler direction, made up of pitch, yaw and roll components.
 *
 * Euler directions are most commonly used by entities to represent the direction they are looking in.
 *
 * @author jk-5
 */
public interface EulerDirection extends Cloneable, Serializable {

    /**
     * Returns the pitch component.
     *
     * @return the pitch component
     */
    float getPitch();

    /**
     * Returns the yaw component.
     *
     * @return the yaw component
     */
    float getYaw();

    /**
     * Returns the roll component.
     *
     * @return the roll component
     */
    float getRoll();

    /**
     * Converts this Euler Direction into a {@link Vector3f}.
     *
     * The vector will represent the directions that this direction is facing in x, y, z coordinates, with a length of 1.
     * Roll will be ignored, as it does not contribute to the facing direction.
     *
     * @return the vector representation of this direction
     */
    Vector3f toVector();

    @Override
    boolean equals(Object o);

    @Override
    int hashCode();

    /**
     * Returns a deep copy of this direction.
     *
     * @return A deep copy
     */
    EulerDirection clone();

    /**
     * Returns a string representation of this direction in the form "(pitch, yaw, roll)".
     *
     * @return This direction as a string
     */
    @Override
    String toString();
}
