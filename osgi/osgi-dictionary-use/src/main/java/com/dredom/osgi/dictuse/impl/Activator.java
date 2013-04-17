package com.dredom.osgi.dictuse.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceEvent;
import org.osgi.framework.ServiceListener;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTracker;

import com.dredom.osgi.dict.api.DictionaryService;

/**
 * Uses Dictionary services previously deployed - or not.
 *
 * This class implements a bundle that uses a dictionary
 * service to check for the proper spelling of a word by
 * checking for its existence in the dictionary. This bundle
 * is more complex than the bundle in Example 3 because it
 * monitors the dynamic availability of the dictionary
 * services. In other words, if the service it is using
 * departs, then it stops using it gracefully, or if it needs
 * a service and one arrives, then it starts using it
 * automatically. As before, the bundle uses the first service
 * that it finds and uses the calling thread of the
 * start() method to read words from standard input.
 * You can stop checking words by entering an empty line, but
 * to start checking words again you must stop and then restart
 * the bundle.
 *
 */
public class Activator implements BundleActivator {

	// Bundle context
	private BundleContext context = null;
	// Service tracker
	private ServiceTracker<?, DictionaryService> tracker;

	public void start(BundleContext bundleContext) throws Exception {
		System.out.println("Starting service #use");

		this.context = bundleContext;

		// Create a service tracker to monitor dictionary service.
    	String filter = "(&(objectClass=" + DictionaryService.class.getName() + ")"
    			+ "(Language=*))";
    	tracker = new ServiceTracker(
				context,
				context.createFilter(filter),
				null);
    	tracker.open();

    	doInput();

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

				// Get the selected dictionary service
				DictionaryService dictionary = tracker.getService();

				// If the user entered a blank line, then
				// exit the loop.
				if (word.length() == 0) {
					break;
				}

				// If there is no dictionary service then say so.
				if (dictionary == null) {
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
		System.out.println("Stopped  service #use.");
	}


}
