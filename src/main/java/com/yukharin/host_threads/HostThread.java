package com.yukharin.host_threads;

import com.yukharin.hosts.Host;

import java.util.concurrent.Callable;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class HostThread implements Callable<Void> {

    private Host host;
    private CyclicBarrier barrier;
    private Semaphore semaphore;
    private Object Void;


    public HostThread(Host host, CyclicBarrier barrier, Semaphore semaphore) {
        this.host = host;
        this.barrier = barrier;
        this.semaphore = semaphore;
    }

    @Override
    public Void call() {
        if (semaphore.tryAcquire()) {
            try {
                host.putItems();
            } finally {
                semaphore.release();
            }
        }
//            semaphore.acquire();
//            host.putItems();
//            semaphore.release();
        return null;
    }
}

