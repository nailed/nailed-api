package jk_5.nailed.api.world;

import java.util.Collection;

import jk_5.nailed.api.gamerule.EditableGameRules;
import jk_5.nailed.api.map.Map;
import jk_5.nailed.api.mappack.metadata.MappackWorld;
import jk_5.nailed.api.player.Player;

/**
 * No description given
 *
 * @author jk-5
 */
public interface World {

    /**
     * Get the dimensionid of this world. This is the id the world is registered to
     *
     * @return dimensionid of this world
     */
    int getDimensionId();

    /**
     * Get the unique name of the map.
     *
     * @return the worlds name
     */
    String getName();

    /**
     * Get the players in the map.
     *
     * @return the player list
     */
    Collection<Player> getPlayers();

    /**
     * What kind of dimension is this world?
     *  -1 for nether
     *   0 for overworld
     *   1 for end
     *
     * Defaults to 0 (overworld)
     *
     * @return the world type
     */
    Dimension getDimension();

    void setMap(Map map);
    Map getMap();

    MappackWorld getConfig();

    EditableGameRules getGamerules(); //TODO: rename this to getGameRules then mixins support multiple methods with the same name

    void onPlayerJoined(Player player);
    void onPlayerLeft(Player player);

    int getTime();
    void setTime(int time);

    WeatherType getWeather();
    void setWeather(WeatherType weather);

    Difficulty getDifficultyValue(); //TODO: rename this to getDifficulty when mixins support multiple methods with the same name
    void setDifficulty(Difficulty difficulty);
}
