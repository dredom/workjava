/**
 *
 */
package com.dredom.security;

import java.io.File;

/**
 * Count files in directory.
 *
 * Testing signed jar security feature.
 * java -Djava.security.manager -cp sec-ex1.jar com.dredom.security.CountFiles .
 *
 */
public class CountFiles {

	public static void main(String[] args) throws Exception {
		File dir = new File(args[0]);
		CountFiles instance = new CountFiles();
		System.out.printf("There are %d files in %s \n", instance.count(dir), dir.toString());
	}

	public int count(File directory) {
		String[] files = directory.list();
		return files.length;
	}
}
