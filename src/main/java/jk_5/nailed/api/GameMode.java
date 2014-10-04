package jk_5.nailed.api;

import java.util.Map;

import com.google.common.collect.Maps;

/**
 * No description given
 *
 * @author jk-5
 */
public enum GameMode {
    SURVIVAL(0),
    CREATIVE(1),
    ADVENTURE(2),
    SPECTATOR(3);

    private final int id;
    private final String name;
    private static final Map<Integer, GameMode> BY_ID = Maps.newHashMap();

    GameMode(int id) {
        this.id = id;
        this.name = this.name().toLowerCase();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static GameMode byId(int id){
        return BY_ID.get(id);
    }

    static {
        for(GameMode gm : values()){
            BY_ID.put(gm.getId(), gm);
        }
    }
}
