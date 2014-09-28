package jk_5.nailed.api.entity;

/**
 * An entity that contains a health attribute.
 */
public interface Damageable {

    /**
     * Subtracts from the health by the given amount.
     *
     * @param amount The damage amount
     */
    void damage(double amount);

    /**
     * Returns the health amount.
     *
     * <p>The range of the health depends on the object on which this
     * method is defined. For players in Minecraft, the nominal range is
     * between 0 and 20, inclusive, but the range can be adjusted.</p>
     *
     * <p>Convention dictates that health does not follow below 0 but this
     * convention may be broken.</p>
     *
     * @return Health value
     */
    double getHealth();

    /**
     * Set the health amount.
     *
     * <p>The range of the health depends on the object on which this
     * method is defined. For players in Minecraft, the nominal range is
     * between 0 and 20, inclusive, but the range can be adjusted.</p>
     *
     * <p>Convention dictates that health does not follow below 0 but this
     * convention may be broken.</p>
     *
     * @param health The health to set to
     */
    void setHealth(double health);

    void heal(double amount);
}
