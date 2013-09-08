package com.dredom.util;

import static java.lang.System.out;

import java.util.Arrays;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Using natural ordering (Comparable).
 *
 */
public class PriorityQueueExample {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Queue<String> stringQ = new PriorityQueue<String>();
		final String[] items = { "how", "now", "cow" };
		
		out.println(Arrays.toString(items));
		for (String item : items) {
			stringQ.add(item);
		}
		out.printf(" PriorityQueue: ");
		Iterator<String> iter = stringQ.iterator();
		while (iter.hasNext()) {
			out.printf("%s, ", iter.next());
		}
		out.println();
		out.printf(" PriorityQueue poll: ");
		String item;
		while (( item = stringQ.poll()) != null) {
			out.printf("%s, ", item );
		}
		out.println();
	}

}
