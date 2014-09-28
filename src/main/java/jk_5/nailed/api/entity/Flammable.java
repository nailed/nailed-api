package jk_5.nailed.api.entity;

/**
 * An entity that can be set on fire.
 *
 * @author jk-5
 */
public interface Flammable {

    /**
     * Returns whether the entity is on fire.
     *
     * @return Whether {@link #getBurnDuration()} is greater than 0
     */
    boolean isBurning();

    /**
     * Gets the remaining time that the entity will be on fire for.
     * 
     * @return The duration in ticks
     */
    int getBurnDuration();

    /**
     * Sets the remaining time for an entity to be on fire.
     * 
     * @param ticks The amount in ticks
     */
    void setBurnDuration(int ticks);
}
