package jk_5.nailed.api.mappack.metadata.json;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import jk_5.nailed.api.mappack.MappackConfigurationException;
import jk_5.nailed.api.mappack.metadata.TutorialStage;
import jk_5.nailed.api.util.Location;

/**
 * No description given
 *
 * @author jk-5
 */
public class JsonTutorialStage implements TutorialStage {

    private final String title;
    private final String[] messages;
    private final Location teleport;

    public JsonTutorialStage(JsonObject json) throws MappackConfigurationException {
        if(!json.has("title")) throw new MappackConfigurationException("Tutorial stage does not have a title");

        title = json.get("title").getAsString();

        if(json.has("messages")){
            List<String> messagesBuilder = new ArrayList<String>();
            for(JsonElement e : json.getAsJsonArray("messages")){
                if(e.isJsonPrimitive()){
                    messagesBuilder.add(e.getAsString());
                }else{
                    throw new MappackConfigurationException("Invalid json element in tutorial messages array: " + e.toString());
                }
            }
            messages = messagesBuilder.toArray(new String[messagesBuilder.size()]);
        }else{
            messages = new String[0];
        }

        if(json.has("teleport") && json.get("teleport").isJsonObject()){
            teleport = Location.read(json.getAsJsonObject("teleport"));
        }else{
            teleport = null;
        }
    }

    @Nonnull
    @Override
    public String title() {
        return title;
    }

    @Nonnull
    @Override
    public String[] messages() {
        return messages;
    }

    @Nullable
    @Override
    public Location teleport() {
        return teleport;
    }
}
