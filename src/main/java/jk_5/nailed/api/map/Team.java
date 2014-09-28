package jk_5.nailed.api.map;

import java.util.Collection;
import javax.annotation.Nonnull;

import jk_5.nailed.api.chat.ChatColor;
import jk_5.nailed.api.player.Player;

/**
 * No description given
 *
 * @author jk-5
 * @see jk_5.nailed.api.mappack.metadata.MappackTeam
 */
public interface Team {

    @Nonnull String id();
    @Nonnull String name();
    @Nonnull ChatColor color();
    @Nonnull Collection<Player> members();
}
