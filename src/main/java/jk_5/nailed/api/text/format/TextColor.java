package jk_5.nailed.api.text.format;

import jk_5.nailed.api.text.Text;

import java.awt.*;

/**
 * Represents the color of the text of a {@link Text}.
 *
 * @see TextColors
 */
public interface TextColor {

    /**
     * Returns the corresponding {@link Color} for this {@link TextColor}.
     *
     * @return The RGB color of this text color
     */
    Color getColor();

    /**
     * Represents a base {@link TextColor} in Minecraft. It can be represented
     * using legacy formatting codes.
     *
     * @see TextColors
     */
    interface Base extends TextColor, BaseFormatting {

    }
}
