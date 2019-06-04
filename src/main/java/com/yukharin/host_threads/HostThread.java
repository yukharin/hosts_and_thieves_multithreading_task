package com.yukharin.host_threads;

import com.yukharin.hosts.Host;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.Callable;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class HostThread implements Callable<Void> {

    private Host host;
    private Semaphore semaphore;
    private CyclicBarrier barrier;


    public HostThread(Host host, Semaphore semaphore, CyclicBarrier barrier) {
        this.host = host;
        this.semaphore = semaphore;
        this.barrier = barrier;
    }

    @Override
    public Void call() {
        try {
            barrier.await();
            semaphore.acquire();
            try {
                host.putItems();
            } finally {
                semaphore.release();
            }
            barrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
        return null;
    }
}

