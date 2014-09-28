package jk_5.nailed.api.world;

/**
 * No description given
 *
 * @author jk-5
 */
public enum WeatherType {
    DRY,
    RAIN,
    THUNDER;

    public boolean isDry(){
        return this == DRY;
    }

    public boolean isRaining(){
        return this == RAIN || this == THUNDER;
    }

    public boolean isThundering(){
        return this == THUNDER;
    }

    public boolean isSunny(){
        return this == DRY;
    }
}
