package com.dredom.lang;

import static java.lang.System.out;

/**
 * The thread must own the object's monitor (synchronized)
 * for Object wait or notify.
 *
 * It seems the wait() must come first. The threads execute asynchronously, in unpredictable order.
 * If the notify() comes first then the wait() is interminable.
 * 
 * Adding max time - wait(100) - would be a failsafe for scenarios where the waiter is not async.
 */
public class WaitNotify {

    private String value;

    public synchronized String getValue() {
        return value;
    }

    public synchronized void setValue(String value) {
        this.value = value;
    }

    // Test
    public static void main(String[] args) throws InterruptedException {
        final WaitNotify wn = new WaitNotify();
        wn.setValue("START");
        Runnable t1 = new Runnable() {
            @Override
            public void run() {
                out.printf(" t1 got old value=%s, setting 'Thread #1' \n", wn.getValue());
                wn.setValue("Thread #1");
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    out.println(" t1 interrupted! " + e);
                }
                synchronized (wn) {
                    out.println(" t1 notify");
                    wn.notify();
                }
            }
        };
        Runnable t2 = new Runnable() {
            @Override
            public void run() {
                out.printf(" t2 got old value=%s \n", wn.getValue());
                synchronized (wn) {
                    out.println(" t2 waiting");
                    try {
                        wn.wait();
                        out.printf(" t2 after wait value=%s\n", wn.getValue());
                    } catch (InterruptedException e) {
                        out.println(" t2 interrupted! " + e);
                    }
                }
                wn.setValue("Thread #2");
            }
        };
        Thread thread2 = new Thread(t2);
        Thread thread1 = new Thread(t1);
        thread2.start();
        thread1.start();
        out.printf("After starts... value=%s \n", wn.getValue());
        thread2.join();
        thread1.join();
        out.printf("All done... value=%s \n", wn.getValue());
    }
}
