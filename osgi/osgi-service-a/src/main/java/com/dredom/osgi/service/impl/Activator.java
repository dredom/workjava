package com.dredom.osgi.service.impl;

import java.util.Dictionary;
import java.util.Hashtable;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;
import org.osgi.util.tracker.ServiceTracker;
import org.osgi.util.tracker.ServiceTrackerCustomizer;

import com.dredom.osgi.service.api.HelloService;


/**
 * Uses ...
 *
 */
public class Activator implements BundleActivator {

	// Bundle context
	private BundleContext context = null;
	// Service tracker
	private ServiceTracker tracker;
    // Registration of Hello service
    private ServiceRegistration registration;

	public void start(BundleContext bundleContext) throws Exception {
		System.out.println("Starting service #hello");

		this.context = bundleContext;

		// Create a service tracker to monitor dictionary service.
//    	String filter = "(" +
//    			"&(objectClass=" + HelloService.class.getName() + ")" +
//    					")";
    	tracker = new ServiceTracker(
				context,
//				context.createFilter(filter),
				HelloService.class.getName(),
//				new Customizer());
				null);
    	tracker.open(true);

    	System.out.printf("size=%d,  trackingCount=%d \n", tracker.size(), tracker.getTrackingCount());
    	System.out.println("serviceReference=" + tracker.getServiceReference());

    	HelloService service = (HelloService) tracker.getService();
    	if (service != null) {
    		service.greet();
    	} else {
    		System.out.println("HelloService not found by service tracker!");
    	}

//		filter = null;
	}

	public void stop(BundleContext context) throws Exception {
		// Automatically unregistered.
		tracker.close();
		tracker = null;
		System.out.println("Stopped  service #hello.");
	}

	/**
	 * Registering the service here seems to conflict with @Service annotation in HelloServiceImpl.
	 * @Service means it will be registered.
	 */
	private class Customizer implements ServiceTrackerCustomizer {

		public Object addingService(ServiceReference reference) {
			Dictionary<String, String> props = null;
			registration = context.registerService(HelloService.class.getName(), new HelloServiceImpl(), props);
			return context.getService(reference);
		}

		public void modifiedService(ServiceReference reference, Object service) {
			// TODO Auto-generated method stub

		}

		public void removedService(ServiceReference reference, Object service) {
			registration.unregister();
			registration = null;
		}

	}
}
