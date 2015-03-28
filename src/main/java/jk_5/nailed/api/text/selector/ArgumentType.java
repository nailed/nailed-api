package jk_5.nailed.api.text.selector;

import com.google.common.base.Optional;

/**
 * Represents the type of an {@link Argument}. This may represent a single
 * argument key in a {@link Selector}, or a compound of multiple argument keys.
 *
 * @param <T> The type for the value of this argument type
 * @see Selector
 * @see Argument
 * @see ArgumentTypes
 */
public interface ArgumentType<T> {

    /**
     * Returns the key associated with this {@link jk_5.nailed.api.text.selector.ArgumentType}. This will be
     * available if the argument type is not a compound of several ones.
     *
     * @return The key of this argument type, if available
     */
    Optional<String> getKey();

    /**
     * Represents an {@link jk_5.nailed.api.text.selector.ArgumentType} that can be inverted.
     *
     * @param <T> The type for the value of this argument type
     * @see jk_5.nailed.api.text.selector.ArgumentType
     * @see Argument.Invertible
     */
    interface Invertible<T> extends ArgumentType<T> {

    }

    /**
     * Represents a compound {@link jk_5.nailed.api.text.selector.ArgumentType} representing a 3-dimensional
     * vector.
     *
     * @param <V> The type of the vector
     * @param <T> The type of the components of the vector
     */
    interface Vector3<V, T> extends ArgumentType<V> {

        /**
         * Gets the {@link jk_5.nailed.api.text.selector.ArgumentType} for the x coordinate of this
         * {@link jk_5.nailed.api.text.selector.ArgumentType.Vector3} {@link jk_5.nailed.api.text.selector.ArgumentType}.
         *
         * @return The x coordinate argument type
         */
        ArgumentType<T> x();

        /**
         * Gets the {@link jk_5.nailed.api.text.selector.ArgumentType} for the y coordinate of this
         * {@link jk_5.nailed.api.text.selector.ArgumentType.Vector3} {@link jk_5.nailed.api.text.selector.ArgumentType}.
         *
         * @return The y coordinate argument type
         */
        ArgumentType<T> y();

        /**
         * Gets the {@link jk_5.nailed.api.text.selector.ArgumentType} for the z coordinate of this
         * {@link jk_5.nailed.api.text.selector.ArgumentType.Vector3} {@link jk_5.nailed.api.text.selector.ArgumentType}.
         *
         * @return The z coordinate argument type
         */
        ArgumentType<T> z();

    }

    /**
     * Represents the holder of two {@link jk_5.nailed.api.text.selector.ArgumentType}s with a minimal and
     * maximal argument type.
     *
     * @param <T> The type of the argument type
     */
    interface Limit<T extends ArgumentType<?>> {

        /**
         * Returns the minimum {@link jk_5.nailed.api.text.selector.ArgumentType} of this {@link jk_5.nailed.api.text.selector.ArgumentType.Limit}.
         *
         * @return The minimum argument type
         */
        T minimum();

        /**
         * Returns the maximum {@link jk_5.nailed.api.text.selector.ArgumentType} of this {@link jk_5.nailed.api.text.selector.ArgumentType.Limit}.
         *
         * @return The maximum argument type
         */
        T maximum();

    }

}
