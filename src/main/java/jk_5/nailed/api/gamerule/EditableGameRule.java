package jk_5.nailed.api.gamerule;

import javax.annotation.Nonnull;

/**
 * No description given
 *
 * @author jk-5
 */
public interface EditableGameRule<T> extends GameRule<T> {

    void setValue(@Nonnull T value);
}
