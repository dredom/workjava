package com.dredom.lang.reflect;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class Servicer {
	private Map<Integer, Datum> data = new HashMap<Integer, Datum>();

	public Datum get(Integer id) {
		return data.get(id);
	}

	public <T> T getGenerically(T type, Object instance, Method method, Object... args) throws Exception {
		T result = (T) method.invoke(instance, args);
		return result;
	}
	public void add(Datum datum) {
		data.put(datum.getId(), datum);
	}
}
