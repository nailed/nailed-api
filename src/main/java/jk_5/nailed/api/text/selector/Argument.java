package jk_5.nailed.api.text.selector;

/**
 * Represents the argument of a {@link Selector selector}. An Argument is one or
 * more key-to-value mappings ({@code key=value}) with the key represented by an
 * {@link ArgumentType}.
 *
 * @param <T> The type of the value
 */
public interface Argument<T> {

    /**
     * Returns the type of this {@link jk_5.nailed.api.text.selector.Argument}.
     *
     * @return The type of this argument
     */
    ArgumentType<T> getType();

    /**
     * Returns the value of this {@link jk_5.nailed.api.text.selector.Argument}.
     *
     * @return The value of this argument
     */
    T getValue();

    /**
     * Converts this {@link jk_5.nailed.api.text.selector.Argument} to a valid argument string.
     *
     * @return The plain argument string
     */
    String toPlain();

    /**
     * Represents an {@link jk_5.nailed.api.text.selector.Argument} which can be inverted to select all
     * targets not matching the filter.
     *
     * @param <T> The type of the value
     * @see jk_5.nailed.api.text.selector.Argument
     */
    interface Invertible<T> extends Argument<T> {

        /**
         * Returns whether this {@link jk_5.nailed.api.text.selector.Argument} is inverted and will select all
         * targets not matching the filter.
         *
         * @return Whether this argument is inverted
         */
        boolean isInverted();

        /**
         * Inverts this {@link jk_5.nailed.api.text.selector.Argument.Invertible} argument and returns the new
         * {@link jk_5.nailed.api.text.selector.Argument}. The new argument will select all targets this
         * argument didn't select.
         *
         * @return The inverted argument
         */
        Invertible<T> invert();

    }

}
