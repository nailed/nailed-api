package jk_5.nailed.api.plugin;

/**
 * No description given
 *
 * @author jk-5
 */
public @interface Configuration {

    String filename() default "settings.conf";

    String defaults() default "defaults.conf";

}
