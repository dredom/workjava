package com.dredom.lang;

import static java.lang.System.out;

public class WaitNotify {

    private String value;

    public synchronized String getValue() {
        return value;
    }

    public synchronized void setValue(String value) {
        this.value = value;
    }

    // Test
    public static void main(String[] args) {
        final WaitNotify wn = new WaitNotify();
        wn.setValue("START");
        Runnable t1 = new Runnable() {
            public void run() {
                out.printf("t1 got old value=%s \n", wn.getValue());
                synchronized (wn) {
                    wn.setValue("Thread #1");
                    try {
                        Thread.sleep(2000L);
                    } catch (InterruptedException e) {
                    }
                    out.println("t1 notify");
                    wn.notify();
                }
            }
        };
        Runnable t2 = new Runnable() {
            public void run() {
                out.printf("t2 got old value=%s \n", wn.getValue());
                synchronized (wn) {
                    out.println("t2 waiting");
                    try {
                        wn.wait();
                        out.printf("t2 after wait value=%s\n", wn.getValue());
                    } catch (InterruptedException e) {
                    }
                }
                wn.setValue("Thread #2");
            }
        };
        new Thread(t2).start();
        new Thread(t1).start();
        out.printf("After starts... value=%s \n", wn.getValue());
    }
}
