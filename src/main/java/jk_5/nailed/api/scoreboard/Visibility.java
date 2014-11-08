package jk_5.nailed.api.scoreboard;

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
}
