package com.dredom;

import static java.lang.System.out;
/**
 * Does a string have all unique characters?
 */
public class IsUnique {
    public static void main(String[] args) throws Exception {
        if (args.length == 0 || args[0].length() == 0) {
            throw new IllegalArgumentException("Please provide string");
        }
        String in = args[0];
        // ASCII

        out.println(in + " " + (checkUnique(in) ? "unique" : "not unique"));
    }

    public static boolean checkUnique(String in) {
        // Iterate through string, save value of each char in bits.
        // if any char already saved, unique -> false
        int track = 0;
        for (int c = 0; c < in.length() - 1; c++) {
            char ch = in.charAt(c);
            if ((track & (1 << ch)) != 0) {
                return false;
            }
            track |= 1 << ch;
        }
        return true;
    }
}
