package jk_5.nailed.api.plugin;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * No description given
 *
 * @author jk-5
 */
@Target(FIELD)
@Retention(RUNTIME)
public @interface Configuration {

    String filename() default "settings.conf";

    String defaults() default "defaults.conf";

}
