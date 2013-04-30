package com.dredom.osgi.servlet.search.impl;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

/**
 * Uses ...
 *
 */
public class Activator implements BundleActivator {


	@Override
	public void start(BundleContext bundleContext) throws Exception {
		System.out.println("Starting bundle #servlet-b");

	}

	@Override
	public void stop(BundleContext context) throws Exception {
		System.out.println("Stopping bundle #servlet-b");
	}

}
