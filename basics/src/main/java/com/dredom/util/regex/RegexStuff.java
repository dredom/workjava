package com.dredom.util.regex;

import static java.lang.System.out;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexStuff {

//    static Pattern patternWord = Pattern.compile("\\b\\S+\\b");
    static Pattern patternWord = Pattern.compile("\\bhow\\b");
    final static String dictionary = "how now brown cow";

    public static void main(String[] args) throws Exception {
        out.println("Dictionary: " + dictionary);
        final String[] inputs = { "ho", "how", "zuul", "brownie" };
        for (String input : inputs) {
            out.println(input + " : " + isWord(input));
        }

    }

    static boolean isWord(String input) {
//        Matcher matcher = patternWord.matcher(input);
//        return matcher.matches();
//        boolean result = Pattern.matches("\\b" + input + "\\b", dictionary);
        Pattern p = Pattern.compile("\\b" + input + "\\b");
        Matcher m = p.matcher(dictionary);
        boolean result = m.find();
        return result;
    }

    static void initializeDictionary() {

    }
}
