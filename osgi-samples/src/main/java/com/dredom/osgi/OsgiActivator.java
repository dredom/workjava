package com.dredom.osgi;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceEvent;
import org.osgi.framework.ServiceListener;

/**
 * This class implements a simple bundle that utilizes the OSGi
 * framework's event mechanism to listen for service events. Upon
 * receiving a service event, it prints out the event's details.
 *
 */
public class OsgiActivator implements BundleActivator, ServiceListener {

	public void start(BundleContext context) throws Exception {
		System.out.println("Starting to listen for service events");
		context.addServiceListener(this);
	}

	public void stop(BundleContext context) throws Exception {
		context.removeServiceListener(this);
		System.out.println("Stopped listening for service events.");
	}

	public void serviceChanged(ServiceEvent event) {
		String[] objectClass = (String[]) event.getServiceReference().getProperty("objectClass");
		int type = event.getType();
		switch (type) {
		case ServiceEvent.REGISTERED:
			System.out.println("Ex1: Service of type " + objectClass[0] + " registered.");
			break;
		case ServiceEvent.UNREGISTERING:
			System.out.println("Ex1: Service of type " + objectClass[0] + " unregistered.");
			break;
		case ServiceEvent.MODIFIED:
			System.out.println("Ex1: Service of type " + objectClass[0] + " modified.");
			break;

		}
	}

}
