package com.dredom.lang.reflect;

import java.lang.reflect.Method;

import org.junit.Before;
import org.junit.Test;

public class ServicerTest {

	Servicer servicer;

	@Before
	public void setup() {
		servicer = new Servicer();
		Datum d1 = new Datum(1, "Numero Uno");
		servicer.add(d1);
	}

	@Test
	public void get() {
		Datum datum = servicer.get(1);
	}

	@Test
	public void getGenerically() throws Exception {
		Integer id = 1;
		Method method = servicer.getClass().getMethod("get", Integer.class);
		Datum type = new Datum();
		Datum result = servicer.getGenerically(type, servicer, method, id);
	}
}
