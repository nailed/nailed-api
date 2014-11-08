package jk_5.nailed.api.plugin;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * No description given
 *
 * @author jk-5
 */
@Target(METHOD)
@Retention(RUNTIME)
public @interface PluginEventHandler {

}
