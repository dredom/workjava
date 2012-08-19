package com.dredom;

public class ReverseString {
    static String INPUT = "ABCD";

    public static void main(String[] args) {
        String input = INPUT;
        char[] chars = input.toCharArray();
        int len = input.length();
        StringBuilder buf = new StringBuilder();
        for (int i = len - 1; i >= 0; i--) {
            buf.append(chars[i]);
        }
        System.out.println(buf);
    }
}
