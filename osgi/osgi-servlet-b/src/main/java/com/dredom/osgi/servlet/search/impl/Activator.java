package com.dredom.osgi.servlet.search.impl;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

/**
 * Uses ...
 *
 */
public class Activator implements BundleActivator {


	@Override
	public void start(BundleContext context) throws Exception {
		System.out.println("Starting " + context.getBundle().getSymbolicName());

	}

	@Override
	public void stop(BundleContext context) throws Exception {
		System.out.println("Stopping " + context.getBundle().getSymbolicName());
	}

}
