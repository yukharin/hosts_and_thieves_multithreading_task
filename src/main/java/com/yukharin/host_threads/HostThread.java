package com.yukharin.host_threads;

import com.yukharin.hosts.Host;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class HostThread implements Runnable {

    private Host host;
    private CyclicBarrier start;
    private CyclicBarrier finish;
    private Semaphore semaphore;

    public HostThread(Host host, CyclicBarrier start, CyclicBarrier finish, Semaphore semaphore) {
        this.host = host;
        this.start = start;
        this.finish = finish;
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        if (start != null) {
            try {
                start.await();
                try {
                    semaphore.acquire();
                    host.putItems();
                } finally {
                    semaphore.release();
                }
                finish.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }
}
