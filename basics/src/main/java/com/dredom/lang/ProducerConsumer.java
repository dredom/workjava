package com.dredom.lang;

import static java.lang.System.out;

public class ProducerConsumer {
    static final int TIMES = 7;

    /**
     * Value container used by both producer and consumer.
     */
    static class Val {
        int value;
        boolean gotvalue;

        public synchronized void set(int value) {
            while (gotvalue) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    out.println(" set interrupted");
                    return;
                }
            }
            this.value = value;
            this.gotvalue = true;
            notify();
        }

        /**
         * Note: Always invoke wait inside a loop that tests for the condition being waited for.
         * Don't assume that the interrupt was for the particular condition you were waiting for,
         * or that the condition is still true.
         */
        public synchronized int get() {
            while (!gotvalue) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    out.println(" get interrupted");
                    return value;
                }
            }
            this.gotvalue = false;
            int val = this.value;
            notify();
            return val;
        }
    }

    /**
     * Producer
     */
    static class Producer implements Runnable {
        final Val container;
        public Producer(Val container) {
            this.container = container;
        }
        public void run() {
            for (int i = 1; i <= TIMES; i++) {
                container.set(i);
                out.println("produced " + i);
            }
        }
    }

    /**
     * Consumer
     */
    static class Consumer implements Runnable {
        final Val container;
        public Consumer(Val container) {
            this.container = container;
        }
        public void run() {
            for (int i = 1; i <= TIMES; i++) {
                int val = container.get();
                out.println("consumed " + val);
            }
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        Val container = new Val();
        Producer p = new Producer(container);
        Consumer c = new Consumer(container);

        new Thread(p, "producer").start();
        new Thread(c, "consumer").start();

    }

}
