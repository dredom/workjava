package com.dredom.osgi.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Constants;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceEvent;
import org.osgi.framework.ServiceListener;
import org.osgi.framework.ServiceReference;

import com.dredom.osgi.dict.api.DictionaryService;

/**
 * Uses Dictionary services previously deployed - or not.
 *
 * Implements BundleActivator.start(). Adds itself as a listener for service
 * events, then queries for available dictionary services. If any dictionaries
 * are found it gets a reference to the first one available and then starts its
 * "word checking loop". If no dictionaries are found, then it just goes
 * directly into its "word checking loop", but it will not be able to check any
 * words until a dictionary service arrives; any arriving dictionary service
 * will be automatically used by the client if a dictionary is not already in
 * use. Once it has dictionary, it reads words from standard input and checks
 * for their existence in the dictionary that it is using.
 *
 */
public class OsgiActivator implements BundleActivator, ServiceListener {

	// Bundle context
	private BundleContext context = null;
	// Service reference being used
	private ServiceReference<?> ref;
	// Service object being used
	private DictionaryService dictionary;


	public void start(BundleContext bundleContext) throws Exception {
		System.out.println("Starting service #1");

		this.context = bundleContext;

        // We synchronize while registering the service listener and
        // performing our initial dictionary service lookup since we
        // don't want to receive service events when looking up the
        // dictionary service, if one exists.
        synchronized (this) {
        	// Listen for events pertaining to dictionary services.
        	String filterListen = "(&(" + Constants.OBJECTCLASS + "=" + DictionaryService.class.getName() + ")"
        			+ "(Language=*))";
        	context.addServiceListener(this, filterListen);

        	// Query for any service references matching any language.
        	String filterQuery = "(Language=*)";
        	ServiceReference<?>[] refs = context.getServiceReferences(DictionaryService.class.getName(), filterQuery);
    		if (refs != null) {
    			this.ref = refs[0];
    			this.dictionary = (DictionaryService) context.getService(ref);
    		} else {
    			System.out.println("No services selected ;-|");
    		}

    		doInput();
        }

		// Query for all service references matching any language.
//		filter = null;
	}

	private void doInput() {
		try {
			System.out.println(" Enter a blank line to end.");
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			String word = "";
			// Loop endlessly.
			while (true) {
				// Ask the user to enter a word.
				System.out.print("Enter word: ");
				word = in.readLine();

				// If the user entered a blank line, then
				// exit the loop.
				if (word.length() == 0) {
					break;
				}

				// If there is no dictionary service then say so.
				if (this.dictionary == null) {
					System.out.println("No dictionary available :|");
				} else if (dictionary.checkWord(word)) {
					System.out.println("Correct.");
				} else {
					System.out.println("Incorrect.");
				}

			}
		} catch (IOException ex) {
		}

	}

	public void stop(BundleContext context) throws Exception {
//		context.removeServiceListener(this);
		// Automatically unregistered.
		System.out.println("Stopped  service #1.");
	}

    /**
     * Implements ServiceListener.serviceChanged(). Checks
     * to see if the service we are using is leaving or tries to get
     * a service if we need one.
     * @param event the fired service event.
    **/
	public synchronized void serviceChanged(ServiceEvent event) {
		System.out.println("serviceChanged  #1 event. " + event);

		String[] objectClass = (String[]) event.getServiceReference().getProperty("objectClass");

		int type = event.getType();
		switch (type) {

		// If s dictionary service was registered, see if we need one.
		// If so, get a reference to it.
		case ServiceEvent.REGISTERED:
			System.out.println("Ex2: Service of type " + objectClass[0] + " registered.");
			if (this.ref == null) {
				// Get a reference to the service object
				this.ref = event.getServiceReference();
				this.dictionary = (DictionaryService) context.getService(ref);
			}
			break;

		// If a dictionary service was unregistered, see if it was the one we were using.
		// If so, unget the service and try to query to get another one.
		case ServiceEvent.UNREGISTERING:
			System.out.println("Ex2: Service of type " + objectClass[0] + " unregistered.");
			if (event.getServiceReference() == this.ref) {
				// Unget service object and null references.
				this.context.ungetService(ref);
				this.ref = null;
				this.dictionary = null;

				// Query to see if we can get another service.
				ServiceReference<?>[] refs = null;
				try {
					refs = context.getServiceReferences(DictionaryService.class.getName(), "(Language=*)");
				} catch (InvalidSyntaxException e) {
					// Never
				}
				if (refs != null) {
					// Get a reference to the first service object.
					this.ref = refs[0];
					this.dictionary = (DictionaryService) context.getService(ref);
				}
			}
			break;
		case ServiceEvent.MODIFIED:
			System.out.println("Ex2: Service of type " + objectClass[0] + " modified.");
			break;

		default:
			System.out.println("Ex2: Unknown service event type " + type);

		}
	}


}
