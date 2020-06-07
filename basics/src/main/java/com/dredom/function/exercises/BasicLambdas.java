package com.dredom.function.exercises;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.BiFunction;

/**
 * http://www.java-programming.info/tutorial/pdf/java/exercises/exercises-lambdas-1.pdf
 * @author andre
 *
 * byte      8 bits      -128 - 127
 * char     16 bits     Unicode
 * short    16 bits     -32768 - 32767
 * int      32 bits     -2^32 - 2^32-1
 * long     64 bits     -2^63 - 2^63-1
 */
public class BasicLambdas {

    public static void main(String[] args) {
        String[] strings = { "abc", "abracadae", "bd", "hoe" };
        System.out.println("Shortest to longest: " + Arrays.asList(shortestToLongest(strings)));
        System.out.println("By first character: " + Arrays.asList(alphabeticallyByFirstChar(strings)));
        System.out.println("Contains 'e' first: " + Arrays.asList(containsEFirst(strings)));
        System.out.println("BetterString longer: " + betterString("abc", "defg", (a, b) -> a.length() > b.length()));
        System.out.println("BetterString first: " + betterString("abc", "defg", (a, b) -> true));
    }

    private static String[] shortestToLongest(String[] strings) {
        Comparator<String> comp = (a, b) -> a.length() < b.length() ? -1 : a.length() > b.length() ? 1 : 0;
        Arrays.sort(strings, comp);
        return  strings;
    }

    /**
     * Alphabetically by first character only.
     */
    private static String[] alphabeticallyByFirstChar(String[] strings) {
        Comparator<String> comp = (String a, String b) -> a.charAt(0) < b.charAt(0) ? -1 : a.charAt(0) > b.charAt(0) ? 1 : 0;
        Arrays.sort(strings, comp);
        return strings;
    }

    /**
     * Strings that contain “e” first, everything else second.
     */
    private static String[] containsEFirst(String[] strings) {
        Comparator<String> comp = (a, b) -> {
            if (a.contains("e")) {
                return -1;
            }
            return 1;
        };
        Comparator<String> comp2 = (String a, String b) -> Util.containsEFirst(a, b);
        Comparator<String> comp3 =  Util::containsEFirst;
        Arrays.sort(strings, comp3);
        return strings;
    }

    private static String betterString(String string1, String string2, BiFunction<String, String, Boolean> func) {
        return func.apply(string1, string2) ? string1 : string2;
    }
}
