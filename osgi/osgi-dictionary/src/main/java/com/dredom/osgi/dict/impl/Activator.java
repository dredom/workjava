package com.dredom.osgi.dict.impl;

import java.util.Hashtable;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceEvent;
import org.osgi.framework.ServiceListener;

import com.dredom.osgi.dict.api.DictionaryService;

/**
 * This class implements a simple bundle that utilizes the OSGi
 * framework's event mechanism to listen for service events. Upon
 * receiving a service event, it prints out the event's details.
 *
 */
public class Activator implements BundleActivator, ServiceListener {

	public void start(BundleContext context) throws Exception {
		System.out.println("Starting to listen for service events #3");
//		context.addServiceListener(this);
		Hashtable<String, String> props = new Hashtable<String, String>();
		props.put("Language", "English");
		DictionaryImpl dictEn = new DictionaryImpl();
		dictEn.setDictionary(new String[] { "welcome", "to", "the", "osgi", "tutorial" } );
		context.registerService(DictionaryService.class.getName(), dictEn, props);

		Hashtable<String, String> props2 = new Hashtable<String, String>();
		props.put("Language", "French");
		DictionaryImpl dictFr = new DictionaryImpl();
		dictEn.setDictionary(new String[] { "bienvenue", "au", "tutoriel", "osgi" } );
		context.registerService(DictionaryService.class.getName(), dictFr, props2);
	}

	public void stop(BundleContext context) throws Exception {
//		context.removeServiceListener(this);
		// Automatically unregistered.
		System.out.println("Stopped listening for service events.");
	}

	public void serviceChanged(ServiceEvent event) {
		String[] objectClass = (String[]) event.getServiceReference().getProperty("objectClass");
		int type = event.getType();
		switch (type) {
		case ServiceEvent.REGISTERED:
			System.out.println("Ex2: Service of type " + objectClass[0] + " registered.");
			break;
		case ServiceEvent.UNREGISTERING:
			System.out.println("Ex2: Service of type " + objectClass[0] + " unregistered.");
			break;
		case ServiceEvent.MODIFIED:
			System.out.println("Ex2: Service of type " + objectClass[0] + " modified.");
			break;

		default:
			System.out.println("Ex2: Unknown service event type " + type);

		}
	}

}
