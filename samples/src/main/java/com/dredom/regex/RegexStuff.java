package com.dredom.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexStuff {
    private static Pattern rangeMilesPattern = Pattern.compile("[0-9]*[Mm]$");  // 10m = 10 miles
    private static Pattern rangeKmPattern = Pattern.compile("[0-9]*[Kk]m$");
    private static Pattern justTheNumberPattern = Pattern.compile("[0-9]*");
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

}
