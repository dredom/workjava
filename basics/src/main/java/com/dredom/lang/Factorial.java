package com.dredom.lang;

/**
 * Compute 5! as 5 x 4 x 3 x 2 x 1.
 * n! = 1.2.3 .. n
 */
public class Factorial {

    static int N = 5;

    /**
     * @param args
     */
    public static void main(String[] args) {
        for (int n = 1; n <= N; n++) {
        	int total = 1;
        	for (int i = 1; i <= n; i++) {
        		total *= i;
        	}
        	System.out.printf(" Factorial %d = %d \n", n, total);
        }
    }

}
