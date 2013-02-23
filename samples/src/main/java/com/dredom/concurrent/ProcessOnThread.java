package com.dredom.concurrent;

import static java.lang.System.out;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * Run a task asynchronously with a trackable status showing progress.
 * Can be cancelled mid flight.
 */
public class ProcessOnThread {

    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {

//        doCallable();
        doFutureTask();
    }

    static void doFutureTask() throws Exception {
        println("FUTURE TASK");
        ExecutorService service = Executors.newSingleThreadExecutor();
        WoofCallable task = new WoofCallable();
        WoofStatus status = new WoofStatus();
        task.setStatus(status);
        WoofFutureTask futureTask = new WoofFutureTask(task);
        Future<?> future = service.submit(futureTask);
        int max = 3;

        for (int i = 0; i < 9; i++) {
            println("future.isDone=" + futureTask.isDone() + status);
            if (futureTask.isDone()) {
                break;
            }
            if (i >= max) {
                println("cancel");
                futureTask.cancel(false);
            }
            Thread.sleep(70L);
        }
        service.awaitTermination(100L, TimeUnit.MILLISECONDS);
        println("future.get=" + (futureTask.isCancelled() ? null : future.get()) + status);

    }

    /**
     * Cancellation here has no effect.  Need FutureTask.
     */
    static void doCallable() throws Exception {
        println("CALLABLE");
        ExecutorService service = Executors.newSingleThreadExecutor();
        WoofCallable task = new WoofCallable();
        WoofStatus status = new WoofStatus();
        task.setStatus(status);
        Future<Integer> future = service.submit(task);
        int max = 3;

        for (int i = 0; i < 9; i++) {
            println("future.isDone=" + future.isDone() + status);
            if (future.isDone()) {
                break;
            }
            if (i >= max) {
                future.cancel(false);
                println("cancel");
            }
            Thread.sleep(70L);
        }
        service.awaitTermination(100L, TimeUnit.MILLISECONDS);
        println("future.get=" + (future.isCancelled() ? null : future.get()) + status);
    }

    static void println(String text) {
        out.println("[main] " + text);
    }
}
