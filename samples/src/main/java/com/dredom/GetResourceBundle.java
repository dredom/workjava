package com.dredom;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class GetResourceBundle {

	ResourceBundle bundle;

	public void useProperties(String filename) throws IOException {
		useProperties(filename, Locale.US);
	}

	public void useProperties(String filename, Locale locale) throws IOException {
		this.bundle = ResourceBundle.getBundle(filename, locale);
	}

	public String get(String key) {
		return bundle.getString(key);
	}
}
