package com.dredom;

import com.google.inject.AbstractModule;

/**
 * Guice bindings.
 * @author auntiedt
 *
 */
public class AppModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(Outputter.class).to(OutputterSysout.class);

	}

}
