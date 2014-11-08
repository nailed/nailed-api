package jk_5.nailed.api.mappack.metadata.json;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nonnull;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import jk_5.nailed.api.mappack.MappackConfigurationException;
import jk_5.nailed.api.mappack.metadata.MappackAuthor;
import jk_5.nailed.api.mappack.metadata.MappackMetadata;
import jk_5.nailed.api.mappack.metadata.MappackTeam;
import jk_5.nailed.api.mappack.metadata.MappackWorld;
import jk_5.nailed.api.mappack.metadata.StatConfig;
import jk_5.nailed.api.mappack.metadata.Tutorial;
import jk_5.nailed.api.mappack.metadata.impl.DefaultMappackAuthor;
import jk_5.nailed.api.mappack.metadata.impl.DefaultMappackWorld;

/**
 * No description given
 *
 * @author jk-5
 */
public class JsonMappackMetadata implements MappackMetadata {

    public static JsonMappackMetadata fromFile(File file) throws MappackConfigurationException {
        FileReader reader = null;
        JsonObject obj = null;
        try{
            reader = new FileReader(file);
            obj = new JsonParser().parse(reader).getAsJsonObject();
        }catch(Exception e){

        }finally{
            if(reader != null){
                try{
                    reader.close();
                }catch(IOException e){

                }
            }
        }
        if(obj == null){
            return null;
        }else{
            return new JsonMappackMetadata(obj);
        }
    }

    private final String name;
    private final String version;
    private final MappackAuthor[] authors;
    private final MappackWorld[] worlds;
    private final MappackTeam[] teams;
    private final Tutorial tutorial;
    private final String gameType;
    private final StatConfig[] stats;

    public JsonMappackMetadata(JsonObject obj) throws MappackConfigurationException {
        if(!obj.has("name")) throw new MappackConfigurationException("Mappack does not have a name");
        if(!obj.has("version")) throw new MappackConfigurationException("Mappack does not have a version");
        if(!obj.has("authors")) throw new MappackConfigurationException("Mappack does not have an authors list");

        this.name = obj.get("name").getAsString();
        this.version = obj.get("version").getAsString();

        {
            List<MappackAuthor> authorBuilder = new ArrayList<MappackAuthor>();
            for(Map.Entry<String, JsonElement> entry : obj.getAsJsonObject("authors").entrySet()){
                String name = entry.getKey();
                if(entry.getValue().isJsonPrimitive()){
                    authorBuilder.add(new DefaultMappackAuthor(name, entry.getValue().getAsString()));
                }else{
                    throw new MappackConfigurationException("Invalid json element in authors list: " + entry.getValue().toString());
                }
            }
            authors = authorBuilder.toArray(new MappackAuthor[authorBuilder.size()]);
        }

        JsonMappackWorld defaultProperties = new JsonMappackWorld(null, (obj.has("defaultProperties") ? obj.getAsJsonObject("defaultProperties") : new JsonObject()), DefaultMappackWorld.INSTANCE);

        {
            List<MappackWorld> worldBuilder = new ArrayList<MappackWorld>();
            if(obj.has("worlds")){
                for(Map.Entry<String, JsonElement> entry : obj.getAsJsonObject("worlds").entrySet()){
                    if(entry.getValue().isJsonObject()){
                        worldBuilder.add(new JsonMappackWorld(entry.getKey(), entry.getValue().getAsJsonObject(), defaultProperties));
                    }else{
                        throw new MappackConfigurationException("Invalid json element in worlds list: " + entry.getValue().toString());
                    }
                }
            }
            worlds = worldBuilder.toArray(new MappackWorld[worldBuilder.size()]);
        }

        {
            List<MappackTeam> teamBuilder = new ArrayList<MappackTeam>();
            if(obj.has("teams")){
                for(JsonElement e : obj.getAsJsonArray("teams")){
                    if(e.isJsonObject()){
                        teamBuilder.add(new JsonMappackTeam(e.getAsJsonObject()));
                    }else{
                        throw new MappackConfigurationException("Invalid json element in teams list: " + e.toString());
                    }
                }
            }
            teams = teamBuilder.toArray(new MappackTeam[teamBuilder.size()]);
        }

        {
            List<StatConfig> statsBuilder = new ArrayList<StatConfig>();
            if(obj.has("stats")){
                for(JsonElement e : obj.getAsJsonArray("stats")){
                    if(e.isJsonObject()){
                        statsBuilder.add(new JsonStatConfig(e.getAsJsonObject()));
                    }else{
                        throw new MappackConfigurationException("Invalid json element in stats list: " + e.toString());
                    }
                }
            }
            stats = statsBuilder.toArray(new StatConfig[statsBuilder.size()]);
        }

        if(obj.has("tutorial")){
            this.tutorial = new JsonTutorial(obj.getAsJsonObject("tutorial"));
        }else{
            this.tutorial = null;
        }

        if(obj.has("gametype")){
            this.gameType = obj.get("gametype").getAsString();
        }else{
            this.gameType = null;
        }

        Set<String> foundNames = new HashSet<String>();
        for(MappackWorld world : worlds){
            if(foundNames.contains(world.name())){
                throw new MappackConfigurationException("There are more than 1 worlds with the same name (" + world.name() + ")");
            }
            foundNames.add(world.name());
        }
        foundNames.clear();
    }

    @Nonnull
    @Override
    public String name() {
        return name;
    }

    @Nonnull
    @Override
    public String version() {
        return version;
    }

    @Nonnull
    @Override
    public MappackAuthor[] authors() {
        return authors;
    }

    @Nonnull
    @Override
    public MappackWorld[] worlds() {
        return worlds;
    }

    @Nonnull
    @Override
    public MappackTeam[] teams() {
        return teams;
    }

    @Nonnull
    @Override
    public Tutorial tutorial() {
        return tutorial;
    }

    @Nonnull
    @Override
    public String gameType() {
        return gameType;
    }

    @Nonnull
    @Override
    public StatConfig[] stats() {
        return stats;
    }
}
