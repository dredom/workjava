package com.dredom.function;

import static java.lang.System.out;

import java.util.function.Function;

/**
 * Use Lambdas.
 * @author andre
 *
 */
public class FunctionSample {

    public static void main(String[] args) {
        Function<String, Integer> stringToInt = x -> Integer.valueOf(x);

        String s = "22";
        out.printf("Input=%s, output=%s \n", s, stringToInt.apply(s));

        Function<Integer, Integer> factorial = x -> {
            int fac = x;
            for (int i = x - 1; i > 0; i--) {
                fac *= i;
            }
            return fac;
        };

        int n = 3;
        out.printf("Factorial of %s = %s \n", n, factorial.apply(n));

    }

}
