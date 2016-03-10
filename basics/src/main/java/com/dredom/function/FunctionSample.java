package com.dredom.function;

import static java.lang.System.out;

import java.util.function.Function;
import java.util.function.Predicate;

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
            int fac = x > 1 ? x : 1;
            for (int i = x - 1; i > 0; i--) {
                fac *= i;
            }
            return fac;
        };

        for (int n = 0; n < 5; n++) {
            out.printf("Factorial of %s = %s \n", n, factorial.apply(n));
        }

        Predicate<String> isEmpty = str -> {
            if (str == null) {
                return true;
            }
            if (str.length() == 0) {
                return true;
            }
            return false;
        };

        s = "one";
        out.printf("'%s' %s empty \n", s, isEmpty.test(s) ? "is" : "is not");
        s = "";
        out.printf("'%s' %s empty \n", s, isEmpty.test(s) ? "is" : "is not");
        s = null;
        out.printf("'%s' %s empty \n", s, isEmpty.test(s) ? "is" : "is not");

        // Runnable
        new Thread( () -> System.out.println("HO!") ).start();

    }

}
