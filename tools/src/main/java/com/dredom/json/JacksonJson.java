package com.dredom.json;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
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
}
