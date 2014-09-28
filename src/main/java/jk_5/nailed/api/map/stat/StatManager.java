package jk_5.nailed.api.map.stat;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * No description given
 *
 * @author jk-5
 */
public interface StatManager {

    @Nullable Stat getStat(String name);
    void fireEvent(@Nonnull StatEvent event);
}
