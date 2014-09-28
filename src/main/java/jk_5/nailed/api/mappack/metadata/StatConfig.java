package jk_5.nailed.api.mappack.metadata;

import java.util.Map;
import javax.annotation.Nonnull;

/**
 * No description given
 *
 * @author jk-5
 */
public interface StatConfig {

    @Nonnull String name();
    @Nonnull String track();
    @Nonnull Map<String, String> attributes();
}
