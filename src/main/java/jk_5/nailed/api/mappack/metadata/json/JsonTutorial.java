package jk_5.nailed.api.mappack.metadata.json;

import java.util.ArrayList;
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

    private TutorialStage[] stages;

    public JsonTutorial(JsonObject json) throws MappackConfigurationException {
        List<TutorialStage> builder = new ArrayList<TutorialStage>();
        if(json.has("stages")){
            for(JsonElement e : json.getAsJsonArray("stages")){
                if(e.isJsonObject()){
                    builder.add(new JsonTutorialStage(e.getAsJsonObject()));
                }else{
                    throw new MappackConfigurationException("Invalid json element in tutorial stages array: " + e.toString());
                }
            }
        }
        stages = builder.toArray(new TutorialStage[builder.size()]);
    }

    @Nonnull
    @Override
    public TutorialStage[] stages() {
        return stages;
    }
}
