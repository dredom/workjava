package com.dredom.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexStuff {
    private static Pattern rangeMilesPattern = Pattern.compile("[0-9]*[Mm]$");  // 10m = 10 miles
    private static Pattern rangeKmPattern = Pattern.compile("[0-9]*[Kk]m$");
    private static Pattern justTheNumberPattern = Pattern.compile("[0-9]*");
    private static Pattern groupingPattern = Pattern.compile("([a-z]+)(?:_*)"); // ?:_ is non-capturing
    private static Pattern embeddedGroupPattern = Pattern.compile("how_now_([a-z]+)_cow");
    /**
     * @param args
     */
    public static void main(String[] args) {
        final String justNum = "5";
        final String numAlpha = "6m";
        final String numM = "17M";
        final String numKm = "121km";
        int result;
        result = parse(justNum);
        result = parse(numAlpha);
        result = parse(numM);
        result = parse(numKm);

        System.out.println(groupValues("how_now_brown_cow"));
        System.out.println(embeddedGroupValue("how_now_brown_cow"));

    }

    static int parse(String input) {
        if (rangeMilesPattern.matcher(input).matches()) {
            Matcher matcher = justTheNumberPattern.matcher(input);
            matcher.find();
            int endi = matcher.end();
            String rangeNum = input.substring(0, endi);
            return Integer.parseInt(rangeNum);
        }
        if (rangeKmPattern.matcher(input).matches()) {
            Matcher matcher = justTheNumberPattern.matcher(input);
            matcher.find();
            int endi = matcher.end();
            String rangeNum = input.substring(0, endi);
            return Integer.parseInt(rangeNum);
        }
        return Integer.parseInt(input);
    }

    static String groupValues(String input) {
        Matcher matcher = groupingPattern.matcher(input);
        StringBuilder buf = new StringBuilder();
        while (matcher.find()) {
            buf.append(matcher.group(1)).append(",");
        }
        return buf.toString();
    }

    static String embeddedGroupValue(String input) {
        Matcher matcher = embeddedGroupPattern.matcher(input);
        matcher.find();
        return matcher.group(1);
    }
}
