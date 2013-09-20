package com.dredom.lang;

/**
 * Background. Terminates when main thread terminates. 
 * When the JVM halts daemon threads are abandoned: 
 * finally blocks are not executed, stacks are not unwound.
 * Not good if I/O operation.
 */
public class DaemonTest {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        new WorkerThread().start() ;
        try {
            Thread.sleep(7500);
        } catch (InterruptedException e) {}
        System.out.println("Main Thread ending") ;
    }

}
class WorkerThread extends Thread {

    public WorkerThread() {
        setDaemon(true) ;   
        		// When false, (i.e. when it's a user thread),
                // the Worker thread continues to run.
                // When true, (i.e. when it's a daemon thread),
                // the Worker thread terminates when the main 
                // thread terminates.
    }

    public void run() {
        int count=0 ;
        while (true) {
            System.out.println("Hello from Worker "+count++) ;
            try {
                sleep(5000);
            } catch (InterruptedException e) {}
        }
    }
}