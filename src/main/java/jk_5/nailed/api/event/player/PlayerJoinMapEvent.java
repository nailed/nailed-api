package jk_5.nailed.api.event.player;

import jk_5.nailed.api.map.Map;
import jk_5.nailed.api.player.Player;

/**
 * No description given
 *
 * @author jk-5
 */
public class PlayerJoinMapEvent extends PlayerEvent {

    private final Map map;

    public PlayerJoinMapEvent(Player player, Map map) {
        super(player);
        this.map = map;
    }

    public Map getMap() {
        return map;
    }
}
