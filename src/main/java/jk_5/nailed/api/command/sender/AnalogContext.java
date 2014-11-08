package jk_5.nailed.api.command.sender;

/**
 * No description given
 *
 * @author jk-5
 */
public final class AnalogContext {

    public static final AnalogContext dummy = new AnalogContext();

    private int power;

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }
}
