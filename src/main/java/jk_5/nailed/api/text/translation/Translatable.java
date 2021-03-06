package jk_5.nailed.api.text.translation;

/**
 * Represents a game object with a corresponding translation.
 */
public interface Translatable {

    /**
     * Returns the Translation that corresponds to this {@link Translatable}.
     *
     * @return The translation of this object
     */
    Translation getTranslation();

}
