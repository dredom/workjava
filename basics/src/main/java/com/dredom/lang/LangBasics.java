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
		out.println(args.length);
		out.println(" 10 to the power of 2 = 10E2");
		out.printf(" 10E0 = %f \t Math.pow(10, 0) \n", Math.pow(10, 0));
		out.printf(" 10E1 = %f \n", Math.pow(10, 1));
		out.printf(" 10E2 = %f \n", Math.pow(10, 2));
		out.printf(" 2 * 10E0 = %f \n", 2 * Math.pow(10, 0) );
		out.printf(" 2 * 10E2 = %f \n", 2 * Math.pow(10, 2) );
		out.printf(" 123.456789 = %E \n", 123.456789 );
		out.printf(" 1 << 2  = %3d \t powers of 2 \n", (1 << 2) );
		out.printf(" 1 << 3  = %3d \t powers of 2 \n", (1 << 3) );
		out.printf(" 2       = bits %4s \n", Integer.toBinaryString(2) );
		out.printf("-1       = bits %4s \n", Integer.toBinaryString(-1) );
		out.printf("-2       = bits %4s \n", Integer.toBinaryString(-2) );
		out.printf(" 3       = bits %4s \n", Integer.toBinaryString(3) );
		int i3s = 3 << 1;
		out.printf(" 3 << 1  = bits %4s = %3d \t shift left \n", Integer.toBinaryString(i3s), i3s );
		out.printf(" %d << 1  = bits %4s = %3d \n", i3s, Integer.toBinaryString(i3s << 1), i3s << 1 );
		int i1 = 1;
		int i2 = 2;
		int i3 = 3;
		out.printf(" %d >>> 1 = bits %4s = %3d \t Unsigned right shift \n", 2, Integer.toBinaryString(2 >>> 1), 2 >>> 1 );
		out.printf(" %d >>> 1 = bits %4s = %3d \t Unsigned right shift \n", 4, Integer.toBinaryString(4 >>> 1), 4 >>> 1 );
		out.printf(" %d & %d   = bits %4s = %3d \t Bitwise AND \n", i3, i2, Integer.toBinaryString(i3 & i2), i3 & i2 );
		out.printf(" %d & %d   = bits %4s = %3d \n", i2, i1, Integer.toBinaryString(i2 & i1), i2 & i1 );
		out.println(" ~  Bitwise complement");
		out.println(" ^  XOR");
		out.println(" |  OR");
		int i = 6, r = 0;
		r |= (i >>> 1);
		out.printf(" %d               = bits %4s \n", i, Integer.toBinaryString(i) );
		out.printf(" r |= (%d >>> 1)  = bits %4s = %3d \n", i, Integer.toBinaryString(r), r );
		out.printf(" 5 %% 2 = remainder %d \n", 5 % 2);
		out.printf(" Integer.MAX_VALUE =  %E \n", Integer.MAX_VALUE * 1.0);
		out.printf(" Long.MAX_VALUE    =  %E \n", Long.MAX_VALUE * 1.0);

		int head = 0;
		int len = 8;
		int tail = 0;
		do {
			head = (head - 1) & (len - 1);
			out.println(head);
		} while (head != tail);
		out.printf(" (-1 & 7) = %d \n", (-1 & 7));
		out.printf(" ( 7 & 7) = %d \n", ( 7 & 7));
		out.printf(" (-7 & 7) = %d \n", (-7 & 7));
		out.printf(" ( 6 & 7) = %d \n", ( 6 & 7));
	}
}
