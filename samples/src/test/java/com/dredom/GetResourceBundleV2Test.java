package com.dredom;

import static org.junit.Assert.*;

import java.util.Locale;

import org.junit.Before;
import org.junit.Test;

public class GetResourceBundleV2Test {

	GetResourceBundle service;

	@Before
	public void setup() {
		service = new GetResourceBundle();
	}

	@Test
	public void good() throws Exception {
		final String filename = "files/msg_v2";
		final String key = "question";
		final String expected = "How now?";

		service.useProperties(filename);
		String result = service.get(key);
		assertEquals(expected, result);
	}

	@Test
	public void goodEnUsQ() throws Exception {
		final String filename = "files/msg_v2";
		final String key = "question";
		final String expected = "How now?";

		service.useProperties(filename, Locale.US);
		String result = service.get(key);
		assertEquals(expected, result);
	}

	@Test
	public void goodEnUsA() throws Exception {
		final String filename = "files/msg_v2";
		final String key = "answer";
		final String expected = "forty-two";

		service.useProperties(filename, Locale.US);
		String result = service.get(key);
		assertEquals(expected, result);
	}

	@Test
	public void goodDeQ() throws Exception {
		final String filename = "files/msg_v2";
		final String key = "question";
		final String expected = "Wie jetzt?";

		service.useProperties(filename, Locale.GERMANY);
		String result = service.get(key);
		assertEquals(expected, result);
	}

	@Test
	public void goodDeA() throws Exception {
		final String filename = "files/msg_v2";
		final String key = "answer";
		final String expected = "forty-two";

		service.useProperties(filename, Locale.GERMANY);
		String result = service.get(key);
		assertEquals(expected, result);
	}
}
