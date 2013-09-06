/**
 * 
 */
package com.dredom.lang;

import static java.lang.System.out;

/**
 * The two's complement of an N-bit number is defined as 
 * the complement with respect to 2 to the N, 
 * in other words the result of subtracting the number from 2 to the N.
 * 
 * 2 = 2 x 10E0
 * 200 = 2 x 10E2
 *
 */
public class LangBasics {

	public static void main(String[] args) {
		out.println(" 10 to the power of 2 = 10E2");
		out.printf(" 10E0 = %f \t Math.pow(10, 0) \n", Math.pow(10, 0));
		out.printf(" 10E1 = %f \n", Math.pow(10, 1));
		out.printf(" 10E2 = %f \n", Math.pow(10, 2));
		out.printf(" 2 * 10E0 = %f \n", 2 * Math.pow(10, 0) );
		out.printf(" 2 * 10E2 = %f \n", 2 * Math.pow(10, 2) );
		out.printf(" 123.456789 = %E \n", 123.456789 );
		out.printf(" bits 2 = %4s \n", Integer.toBinaryString(2) );
		out.printf(" bits 3 = %4s \n", Integer.toBinaryString(3) );
		int i3s = 3 << 1;
		out.printf(" bits 3 << 1 = %4s = %3d \t shift left \n", Integer.toBinaryString(i3s), i3s );
		out.printf(" bits %d << 1 = %4s = %3d \n", i3s, Integer.toBinaryString(i3s << 1), i3s << 1 );
		int i3 = 3;
		int i2 = 2;
		int i1 = 1;
		out.printf(" bits %d & %d  = %4s = %3d \t Bitwise AND \n", i3, i2, Integer.toBinaryString(i3 & i2), i3 & i2 );
		out.printf(" bits %d & %d  = %4s = %3d \n", i2, i1, Integer.toBinaryString(i2 & i1), i2 & i1 );
		out.println(" ~  Bitwise complement");
		out.println(" ^  XOR");
		out.println(" |  OR");
		out.printf(" 5 %% 2 = remainder %d \n", 5 % 2);
	}

}
