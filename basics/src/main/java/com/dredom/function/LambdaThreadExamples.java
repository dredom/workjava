package com.dredom.function;

import static java.lang.System.out;

/**
 * Functional interface on Runnable, plus Thread implementations with lambda.
 */
public class LambdaThreadExamples {

    public static void main(String[] args) {
        // Runnable lambda
        Runnable run1 = () -> {
            out.println("Runnable run1");
        };
        new Thread(run1).start();

        // Thread lambda directly (bypass Runnable)
        Thread thread1 = new Thread(() -> {
            out.println("Thread thread1");
        });
        thread1.start();

        LambdaThreadExamples instance = new LambdaThreadExamples();
        instance.methodReference();
    }

    private void methodReference() {
        // Regular lambda
        new Thread(() -> {
            foo();
        }).start();

        // Method reference - running single method with no arguments.
        new Thread(this::foo).start();

    }

    private void foo() {
        out.println(" method foo");
    }
}
