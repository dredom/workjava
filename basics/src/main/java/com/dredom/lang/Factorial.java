package com.dredom.lang;

/**
 * n! = 1.2.3 .. n
 */
public class Factorial {

    static int N = 3;

    /**
     * @param args
     */
    public static void main(String[] args) {
        int total = 1;
        for (int i = 2; i <= N; i++) {
            total = total * i;
        }
        System.out.println("Factorial " + N + " = " + total);
    }

}
