package jk_5.nailed.api.scoreboard;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * No description given
 *
 * @author jk-5
 */
public enum Visibility {
    ALWAYS("always", 0),
    NEVER("never", 1),
    HIDE_FOR_OTHER_TEAMS("hideForOtherTeams", 2),
    HIDE_FOR_OWN_TEAM("hideForOwnTeam", 3);

    private static final Map<String, Visibility> BY_NAME = Maps.newHashMap();
    private final String name;
    private final int id;

    Visibility(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public static Visibility getByName(String name){
        return BY_NAME.get(name.toLowerCase());
    }

    static {
        for(Visibility v : values()){
            BY_NAME.put(v.name.toLowerCase(), v);
        }
    }
}
