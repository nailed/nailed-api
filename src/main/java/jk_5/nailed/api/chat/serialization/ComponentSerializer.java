/*
 * Nailed, a Minecraft PvP server framework
 * Copyright (C) jk-5 <http://github.com/jk-5/>
 * Copyright (C) Nailed team and contributors <http://github.com/nailed/>
 *
 * This program is free software: you can redistribute it and/or modify it
 * under the terms of the MIT License.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License
 * for more details.
 *
 * You should have received a copy of the MIT License along with
 * this program. If not, see <http://opensource.org/licenses/MIT/>.
 */

package jk_5.nailed.api.chat.serialization;

import java.lang.reflect.Type;
import java.util.HashSet;
import javax.annotation.Nonnull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import jk_5.nailed.api.chat.BaseComponent;
import jk_5.nailed.api.chat.TextComponent;
import jk_5.nailed.api.chat.TranslatableComponent;
import jk_5.nailed.api.util.Checks;

public class ComponentSerializer implements JsonDeserializer<BaseComponent> {

    private final static Gson gson = new GsonBuilder().
            registerTypeAdapter(BaseComponent.class, new ComponentSerializer()).
            registerTypeAdapter(TextComponent.class, new TextComponentSerializer()).
            registerTypeAdapter(TranslatableComponent.class, new TranslatableComponentSerializer()).
            create();

    public final static ThreadLocal<HashSet<BaseComponent>> serializedComponents = new ThreadLocal<HashSet<BaseComponent>>();

    @Nonnull
    public static BaseComponent[] parse(@Nonnull String json) {
        Checks.notNull(json, "json");
        if(json.startsWith("[")){ //Array
            return gson.fromJson(json, BaseComponent[].class);
        }
        return new BaseComponent[]{
            gson.fromJson(json, BaseComponent.class)
        };
    }

    @Nonnull
    public static String toString(@Nonnull BaseComponent component) {
        Checks.notNull(component, "component");
        return gson.toJson(component);
    }

    @Nonnull
    public static String toString(@Nonnull BaseComponent... components) {
        Checks.notNull(components, "components");
        Checks.noNullElements(components, "components");
        return gson.toJson(new TextComponent(components));
    }

    @Nonnull
    @Override
    public BaseComponent deserialize(@Nonnull JsonElement json, @Nonnull Type typeOfT, @Nonnull JsonDeserializationContext context) throws JsonParseException {
        if(json.isJsonPrimitive()){
            return new TextComponent(json.getAsString());
        }
        JsonObject object = json.getAsJsonObject();
        if(object.has("translate")){
            return context.deserialize(json, TranslatableComponent.class);
        }
        return context.deserialize(json, TextComponent.class);
    }
}
