package jk_5.nailed.api.entity;

/**
 * An entity that contains a hunger level.
 *
 * @author jk-5
 */
public interface Feedable {
    
    /**
     * Gets the current hunger level.
     * 
     * @return The hunger level
     */
    double getHunger();
    
    /**
     * Gets the current saturation level.
     * 
     * @return The saturation level
     */
    double getSaturation();

    /**
     * Sets the current hunger level.
     * 
     * @param hunger The hunger level
     */
    void setHunger(double hunger);

    /**
     * Sets the current saturation level.
     * 
     * @param saturation The saturation level
     */
    void setSaturation(double saturation);
}
