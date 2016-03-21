package fr.irit.smac.amasfactory.util.parser.deserializer.impl;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import fr.irit.smac.amasfactory.IJsonInitialisable;

public class AmasFactoryDeserializer <T> implements JsonDeserializer<T>{

    protected static final String CONFIGURATION = "configuration";
    protected static final String CLASS_NAME = "className";
    
	@SuppressWarnings("unchecked")
	public T deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
			throws JsonParseException {
		
		JsonObject jobject = (JsonObject) json;

		String classPath = jobject.get(CLASS_NAME).getAsString();
		Class<T> cls;
		try {
			cls = (Class<T>) Class.forName(classPath);
			
			JsonElement configuration = json.getAsJsonObject().get(CONFIGURATION);
			
			if (configuration == null) {
				configuration = new JsonObject();
			}
			return (T) context.deserialize(configuration, cls);

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

}
