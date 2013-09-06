/**
 * 
 */
package com.dredom.lang;

import static java.lang.System.out;

/**
 *
 */
public class MathBasics {

	public static void main(String[] args) {
		int iResult = 5 / 2;
		double fResult = 5 / 2;
		out.printf(" 5 / 2 = %d int \n", iResult);
		out.printf(" 5 / 2 = %f double \n", fResult);
		fResult = 5 / 2.0;
		out.printf(" 5 / 2.0 = %f double \n", fResult);
		long lResult = Math.round( 5 / 2.0);
		out.printf(" Math.round( 5 / 2.0 ) = %d long \n", lResult);
	}
}
