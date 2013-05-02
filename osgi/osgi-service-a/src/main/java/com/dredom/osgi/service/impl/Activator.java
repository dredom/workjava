package com.dredom.osgi.service.impl;

import java.util.Dictionary;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;
import org.osgi.util.tracker.ServiceTracker;
import org.osgi.util.tracker.ServiceTrackerCustomizer;

import com.dredom.osgi.service.api.HelloService;


/**
 * Uses ...
 * Testing with
 *  /apps/geometrixx/components/homepage/content.jsp
 *
 * Question: Is this Activator even needed if the service has annotations for Component and Service?
 *
 */
public class Activator implements BundleActivator {

	// Bundle context
	private BundleContext context;
	// Service tracker
	private ServiceTracker tracker;
    // Registration of Hello service
    private ServiceRegistration registration;

	@Override
	public void start(BundleContext context) throws Exception {
		this.context = context;
		System.out.println("Starting " + context.getBundle().getSymbolicName());


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

	@Override
	public void stop(BundleContext context) throws Exception {
		// Automatically unregistered.
		tracker.close();
		tracker = null;
		this.context = null;
		System.out.println("Stopped " + context.getBundle().getSymbolicName());
	}

	/**
	 * Registering the service here seems to conflict with @Service annotation in HelloServiceImpl.
	 * @Service means it will be registered.
	 */
	private class Customizer implements ServiceTrackerCustomizer {

		@Override
		public Object addingService(ServiceReference reference) {
			Dictionary<String, String> props = null;
			registration = context.registerService(HelloService.class.getName(), new HelloServiceImpl(), props);
			return context.getService(reference);
		}

		@Override
		public void modifiedService(ServiceReference reference, Object service) {
			// TODO Auto-generated method stub

		}

		@Override
		public void removedService(ServiceReference reference, Object service) {
			registration.unregister();
			registration = null;
		}

	}
}
