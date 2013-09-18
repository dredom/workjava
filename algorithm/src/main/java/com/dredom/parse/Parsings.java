/**
 * 
 */
package com.dredom.parse;

/**
 * Parse stuff.
 *
 */
public class Parsings {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		final String[] ints = {
				"123", "-123", "abc"
		};
		for (String in : ints) {
			System.out.printf(" %s -> %d \n", in, parseIntFromString(in));
		}
	}
	
	/**
	 * Without using language method parsing, parse an integer from string.
	 *  "123" => 123
	 *  "-234" => -234
	 *  "abc" => error
	 */
	public static int parseIntFromString(String in) {
		byte[] bytes = in.getBytes();
		// validate
		for (int i = 0; i < bytes.length; i++) {
			byte b = bytes[i];
			if (b == '-' && i == 0) {
				continue;
			}
			if (b < '0' || b > '9') {
				throw new IllegalArgumentException("Not numeric");
			}
		}
		// parse
		int result = 0;
		int stop = 0;
		int sign = 1;
		if (bytes[0] == '-') {
			sign = -1;
			stop = 1;
		}
		int base = 1;
		for (int i = bytes.length - 1; i >= stop; i--) {
			byte b = bytes[i];
			result += (b - '0') * base;
			base *= 10;
		}
		return result * sign;
	}

}
