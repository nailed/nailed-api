package jk_5.nailed.api.event.player;

import jk_5.eventbus.Event;
import jk_5.nailed.api.item.ItemStack;
import jk_5.nailed.api.player.Player;

/**
 * No description given
 *
 * @author jk-5
 */
@Event.Cancelable
public class PlayerRightClickItemEvent extends PlayerEvent {

    private final ItemStack stack;

    public PlayerRightClickItemEvent(Player player, ItemStack stack) {
        super(player);
        this.stack = stack;
    }

    public ItemStack getStack() {
        return stack;
    }
}
