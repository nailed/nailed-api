package jk_5.nailed.api.event.player;

import jk_5.nailed.api.chat.BaseComponent;
import jk_5.nailed.api.chat.ChatColor;
import jk_5.nailed.api.chat.TextComponent;
import jk_5.nailed.api.player.Player;

/**
 * No description given
 *
 * @author jk-5
 */
public class PlayerJoinServerEvent extends PlayerEvent {

    private BaseComponent message;

    public PlayerJoinServerEvent(Player player) {
        super(player);

        message = new TextComponent("");
        TextComponent joined = new TextComponent(" joined the server");
        joined.setColor(ChatColor.YELLOW);
        message.addExtra(player.getDescriptionComponent());
        message.addExtra(joined);
    }

    public BaseComponent getMessage() {
        return message;
    }

    public void setMessage(BaseComponent message) {
        this.message = message;
    }
}
