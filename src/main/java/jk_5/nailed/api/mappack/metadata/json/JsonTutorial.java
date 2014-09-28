package jk_5.nailed.api.mappack.metadata.json;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.annotation.Nonnull;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import jk_5.nailed.api.mappack.MappackConfigurationException;
import jk_5.nailed.api.mappack.metadata.Tutorial;
import jk_5.nailed.api.mappack.metadata.TutorialStage;

/**
 * No description given
 *
 * @author jk-5
 */
public class JsonTutorial implements Tutorial {

    private List<TutorialStage> stages = new ArrayList<TutorialStage>();

    public JsonTutorial(JsonObject json) throws MappackConfigurationException {
        if(json.has("stages")){
            for(JsonElement e : json.getAsJsonArray("stages")){
                if(e.isJsonObject()){
                    stages.add(new JsonTutorialStage(e.getAsJsonObject()));
                }else{
                    throw new MappackConfigurationException("Invalid json element in tutorial stages array: " + e.toString());
                }
            }
        }
    }

    @Nonnull
    @Override
    public Collection<TutorialStage> stages() {
        return stages;
    }
}
