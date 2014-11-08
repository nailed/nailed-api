package jk_5.nailed.api.mappack.metadata.json;

import com.google.gson.JsonObject;

import jk_5.nailed.api.chat.ChatColor;
import jk_5.nailed.api.mappack.MappackConfigurationException;
import jk_5.nailed.api.mappack.metadata.MappackTeam;

/**
 * No description given
 *
 * @author jk-5
 */
public class JsonMappackTeam implements MappackTeam {

    private final String id;
    private final String name;
    private final ChatColor color;

    public JsonMappackTeam(JsonObject json) throws MappackConfigurationException {
        if(!json.has("id")) throw new MappackConfigurationException("Invalid team. Team doesn't have an id");
        if(!json.has("name")) throw new MappackConfigurationException("Invalid team. Team doesn't have a name");
        if(!json.has("color")) throw new MappackConfigurationException("Invalid team. Team doesn't have a color");

        id = json.get("id").getAsString();
        name = json.get("name").getAsString();
        color = ChatColor.getByName(json.get("color").getAsString());

        if(color == null) throw new MappackConfigurationException("Invalid team color '" + json.get("color").getAsString() + "'");
    }

    @Override
    public String id() {
        return id;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public ChatColor color() {
        return color;
    }
}
