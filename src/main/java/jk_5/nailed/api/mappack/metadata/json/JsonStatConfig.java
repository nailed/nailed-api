package jk_5.nailed.api.mappack.metadata.json;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nonnull;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import jk_5.nailed.api.mappack.MappackConfigurationException;
import jk_5.nailed.api.mappack.metadata.StatConfig;

/**
 * No description given
 *
 * @author jk-5
 */
public class JsonStatConfig implements StatConfig {

    private final String name;
    private final String track;
    private final Map<String, String> attributes;

    public JsonStatConfig(JsonObject json) throws MappackConfigurationException {
        if(!json.has("name")) throw new MappackConfigurationException("Stat does not have a name");

        name = json.get("name").getAsString();
        track = (json.has("track") ? json.get("track").getAsString() : null);
        attributes = new HashMap<String, String>();
        if(json.has("attributes")){
            for(Map.Entry<String, JsonElement> e : json.getAsJsonObject("attributes").entrySet()){
                attributes.put(e.getKey(), e.getValue().getAsString());
            }
        }
    }

    @Nonnull
    @Override
    public String name() {
        return name;
    }

    @Nonnull
    @Override
    public String track() {
        return track;
    }

    @Nonnull
    @Override
    public Map<String, String> attributes() {
        return attributes;
    }
}
