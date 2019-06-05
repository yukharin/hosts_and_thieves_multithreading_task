package com.yukharin.threads;

import com.yukharin.entities.Host;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class HostThread implements Runnable {

    private Host host;
    private Semaphore semaphore;
    private CyclicBarrier barrier;
    private AtomicInteger counter;


    public HostThread(Host host, Semaphore semaphore, CyclicBarrier barrier, AtomicInteger counter) {
        this.host = host;
        this.semaphore = semaphore;
        this.barrier = barrier;
        this.counter = counter;
    }

    @Override
    public void run() {
        try {
            barrier.await();
            semaphore.acquire();
            try {
                counter.incrementAndGet();
                host.putItems();
                System.out.println("Hosts inside home: " + counter.intValue());
                counter.decrementAndGet();
            } finally {
                semaphore.release();
            }
            barrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}

