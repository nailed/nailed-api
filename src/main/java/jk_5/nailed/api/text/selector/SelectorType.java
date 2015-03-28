package jk_5.nailed.api.text.selector;

/**
 * Represents a selector type.
 *
 * @see Selectors
 */
public interface SelectorType {

    /**
     * Returns the ID of this {@link jk_5.nailed.api.text.selector.SelectorType}. For example, {@code @a}
     * would return {@code "a"}.
     *
     * @return The ID of this {@link jk_5.nailed.api.text.selector.SelectorType}
     */
    String getId();

}
