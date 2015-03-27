package com.dredom.json;

import java.io.File;
import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * Convert Object to JSON string - using Jackson.
 */
public class JacksonJson {

	public String toJson(Object data) throws JsonGenerationException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		String result = mapper.writeValueAsString(data);
		return result;
	}

    public Object toObject(String data, Class<?> clazz) throws JsonParseException, JsonMappingException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        Object obj = mapper.readValue(data, clazz);
        return obj;
    }
    public <T> T toObject(File data, Class<T> clazz) throws JsonParseException, JsonMappingException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        T obj = mapper.readValue(data, clazz);
        return obj;
    }
}
