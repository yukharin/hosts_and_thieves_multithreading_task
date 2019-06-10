package com.yukharin.hosts_and_thieves.threads;

import com.yukharin.hosts_and_thieves.entities.Home;
import com.yukharin.hosts_and_thieves.entities.Host;

import java.util.Objects;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class HostThread implements Runnable {

    private Host host;
    private Home home;
    private Semaphore semaphore;
    private CyclicBarrier barrier;
    private AtomicInteger counter;


    public HostThread(Host host, Home home, Semaphore semaphore, CyclicBarrier barrier, AtomicInteger counter) {
        this.host = Objects.requireNonNull(host);
        this.semaphore = Objects.requireNonNull(semaphore);
        this.barrier = Objects.requireNonNull(barrier);
        this.counter = Objects.requireNonNull(counter);
        this.home = Objects.requireNonNull(home);
    }

    @Override
    public void run() {
        try {
            barrier.await();
            putItem();
            barrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

    private void putItem() throws InterruptedException {
        while (host.haveItems()) {
            semaphore.acquire();
            try {
                counter.incrementAndGet();
                host.putItem(home);
                System.out.println("Hosts inside home: " + counter.intValue());
                counter.decrementAndGet();
            } finally {
                semaphore.release();
            }
        }
    }

    private void putItems() throws InterruptedException {
        semaphore.acquire();
        try {
            counter.incrementAndGet();
            host.putItems(home);
            System.out.println("Hosts inside home: " + counter.intValue());
            counter.decrementAndGet();
        } finally {
            semaphore.release();
        }
    }


}

