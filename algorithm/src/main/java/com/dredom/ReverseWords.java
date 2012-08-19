package com.dredom;

public class ReverseWords {

    static String INPUT = "How now brown cow";

    /**
     * @param args
     */
    public static void main(String[] args) {
        String input = INPUT;
        System.out.println(plain(input));
    }

    static String plain(String input) {
        String[] words = input.split(" ");
        StringBuilder buf = new StringBuilder();
        for (int i = words.length - 1; i >= 0; i--) {
            if (buf.length() > 0) {
                buf.append(' ');
            }
            buf.append(words[i]);
        }
        return buf.toString();
    }
}
