package jk_5.nailed.api.entity;

/**
 * Represents an entity that can have its maximum health scaled up or down.
 *
 * @author jk-5
 */
public interface HealthScalable {

    /**
     * Gets the maximum amount of health.
     *
     * @return The maximum amount of health
     */
    double getMaxHealth();

    /**
     * Sets the maximum health amount.
     *
     * @param maxHealth The maximum health amount
     */
    void setMaxHealth(double maxHealth);

    /**
     * Resets the maximum health amount back to the default value
     */
    void resetMaxHealth();

}
