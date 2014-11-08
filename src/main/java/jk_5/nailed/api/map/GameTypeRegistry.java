package jk_5.nailed.api.map;

import java.util.Collection;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * No description given
 *
 * @author jk-5
 */
public interface GameTypeRegistry {

    void registerGameType(@Nonnull GameType gameType);
    void unregisterGameType(@Nonnull GameType gameType);

    @Nonnull Collection<GameType> getTypes();
    @Nullable GameType getByName(String name);
}
