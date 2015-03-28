package jk_5.nailed.api.text.translation;

import jk_5.nailed.api.text.translation.locale.Locales;

/**
 * Represents an identifier for text that can be translated into multiple
 * languages. This is usually sent directly to the client and translated there,
 * not on the server.
 *
 * <p>Some translations require parameters to be sent together with them, if
 * they're not given they will be filled with empty text.</p>
 *
 * <p>While the client has multiple locales available, most implementations
 * support only {@link Locales#ENGLISH}.</p>
 */
public interface Translation {

    /**
     * Returns identifier for this {@link Translation}.
     *
     * @return The translation identifier of this translation
     */
    String getId();

    /**
     * Gets the default translation without any parameters. If the translations
     * contains any parameters they will be filled with empty text.
     *
     * @return The default translation without any parameters
     */
    String get();

    /**
     * Gets the default translation with the specified parameters.
     *
     * @param args The parameters for this translation
     * @return The default translation with the specified parameters
     */
    String get(Object... args);

    // This would only work on the client, the server has only the English
    // translation
    // String get(Locale locale);
    // String get(Locale locale, Object... args);

}
