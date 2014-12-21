package com.dredom;

/**
 * String is immutable, based on final char[].
 * charAt(i) will return the primitive value.
 *
 * @author andre
 *
 */
public class ReverseString {
    static String INPUT = "ABCD";

    public static void main(String[] args) {
        String input = INPUT;
        int len = input.length();
        char[] out = new char[len];
        for (int j = 0, k = len - 1; j < len; j++, k--) {
        	out[k] = input.charAt(j);
        }

        System.out.println(out);
    }
}
