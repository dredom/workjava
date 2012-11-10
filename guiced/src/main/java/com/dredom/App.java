package com.dredom;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;

/**
 * Hello world!
 *
 */
public class App {
	private final Outputter outputter;

	public static void main(String[] args) {
		Injector injector = Guice.createInjector(new AppModule());

		App app = injector.getInstance(App.class);
		app.doit("Hello Goose");
	}

	@Inject
	public App(Outputter outputter) {
		this.outputter = outputter;
	}

	public void doit(String param) {
		outputter.output(param);
	}
}
