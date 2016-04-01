package com.dredom.concurrent;

import java.util.concurrent.FutureTask;
/**
 * Wrapping the WoofCallable in a FutureTask allows us to set a "cancel" flag.
 */
public class WoofFutureTask extends FutureTask<WoofStatus> {

    private WoofCallable callable;

    public WoofFutureTask(WoofCallable callable) {
        super(callable);
        this.callable = callable;
    }
    public WoofFutureTask(WoofCallable callable, WoofStatus status) {
        this(callable);
        this.callable.setStatus(status);
    }

    /**
     * Because we do not super.setCancelled() the FutureTask itself will not reflect isCancelled().
     * If we do super.setCancelled() then the task can get killed before it gracefully ends.
     */
    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        callable.setCancelled();
//        return super.cancel(mayInterruptIfRunning);
        return true;
    }

}
