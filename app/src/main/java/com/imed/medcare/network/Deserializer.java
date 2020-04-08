package com.imed.medcare.network;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;


class Deserializer<T> implements JsonDeserializer<T> {
    @Override
    public T deserialize(JsonElement je, Type type, JsonDeserializationContext jdc)
            throws JsonParseException {

        JsonObject jo = je.getAsJsonObject();
        for (int i = 0; i < jo.get("data").getAsJsonArray().get(0).getAsJsonObject().get("questions").getAsJsonArray().size(); i++) {
            if (jo.get("data").getAsJsonArray().get(0).getAsJsonObject().get("questions").getAsJsonArray().get(i).getAsJsonObject().get("measurement").isJsonPrimitive()) {
                JsonObject jsonObject = new JsonObject();
                jo.get("data").getAsJsonArray().get(0).getAsJsonObject().get("questions").getAsJsonArray().get(i).getAsJsonObject().add("measurement", jsonObject);
            }
        }
        return new Gson().fromJson(jo, type);
    }
}