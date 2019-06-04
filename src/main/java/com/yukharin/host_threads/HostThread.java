package com.yukharin.host_threads;

import com.yukharin.hosts.Host;

import java.util.concurrent.Callable;
import java.util.concurrent.Semaphore;

public class HostThread implements Callable<Void> {

    private Host host;
    private Semaphore semaphore;


    public HostThread(Host host, Semaphore semaphore) {
        this.host = host;
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
        return null;
    }
}

