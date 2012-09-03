package com.dredom.lang;

import static java.lang.System.out;

public class ProducerConsumer {
    static final int TIMES = 10;

    static class Val {
        int value;
        boolean gotvalue;
        public synchronized void set(int value) {
            if (gotvalue) {
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
        public synchronized int get() {
            if (!gotvalue) {
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

    static class Producer implements Runnable {
        final Val container;
        public Producer(Val container) {
            this.container = container;
        }
        public void run() {
            for (int i = 1; i <= TIMES; i++) {
                out.println("producing " + i);
                container.set(i);
            }
        }
    }

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
