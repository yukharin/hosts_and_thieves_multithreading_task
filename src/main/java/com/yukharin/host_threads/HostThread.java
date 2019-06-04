package com.yukharin.host_threads;

import com.yukharin.hosts.Host;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class HostThread implements Runnable {

    private Host host;
    private CyclicBarrier barrier;
    private Semaphore semaphore;


    public HostThread(Host host, CyclicBarrier barrier, Semaphore semaphore) {
        this.host = host;
        this.barrier = barrier;
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        try {
            barrier.await();
            if (semaphore.tryAcquire()) {
                try {
                    host.putItems();
                } finally {
                    semaphore.release();
                }
            }
            barrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}
