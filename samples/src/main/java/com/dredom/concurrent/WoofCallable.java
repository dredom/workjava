package com.dredom.concurrent;

import static java.lang.System.out;

import java.util.Random;
import java.util.concurrent.Callable;

public class WoofCallable implements Callable<WoofStatus> {

    private WoofStatus status = new WoofStatus();

    private volatile boolean cancelled;

    @Override
    public WoofStatus call() throws Exception {
        int count = 0;
        int times = 1 + new Random().nextInt(5);
        for (int i = 0; i < times; i++) {
            for (int j = 0; j < i; j++) {
                out.print("\t");
            }
            out.println("woof");
            status.setStatus(++count + " woofs");

            if (cancelled) {
                out.println("\t \\ interrupted /");
                status.setStatus("interrupted");
                break;
            }
            Thread.sleep(200L);
        }
        out.println("WoofCallable ending");
        return status;
    }



    public void setStatus(WoofStatus status) {
        this.status = status;
    }




    public void setCancelled() {
        this.cancelled = true;
        out.println("WoofCallable.setCancelled()");
    }

}
