package jk_5.nailed.api.entity;

/**
 * An entity that contains a level and can acquire experience.
 *
 * @author jk-5
 */
public interface LevelProgressable {

    /**
     * Gets the experience value.
     * 
     * @return The experience value
     */
    double getExperience();

    /**
     * Returns the level value.
     * 
     * @return The level value
     */
    int getLevel();

    /**
     * Sets the experience value.
     * 
     * @param experience The experience value
     */
    void setExperience(double experience);

    /**
     * Sets the level value.
     * 
     * @param level The Level value
     */
    void setLevel(int level);

}
