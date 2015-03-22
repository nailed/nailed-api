package jk_5.nailed.api.plugin;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * An annotation used to describe and mark a Sponge plugin
 *
 * @author jk-5
 */
@Target(TYPE)
@Retention(RUNTIME)
public @interface Plugin {

    /**
     * An ID for the plugin to be used internally. The ID should be unique as to
     * not conflict with other plugins.
     *
     * @return The id of the plugin
     */
    String id();

    /**
     * The human readable name of the plugin as to be used in descriptions and
     * similar things.
     *
     * @return The human readable name of the plugin
     */
    String name();

    /**
     * The version of the plugin.
     *
     * @return The version of the plugin
     */
    String version() default "unknown";
}
