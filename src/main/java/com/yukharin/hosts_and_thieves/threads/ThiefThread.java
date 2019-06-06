package com.yukharin.hosts_and_thieves.threads;

import com.yukharin.hosts_and_thieves.entities.Home;
import com.yukharin.hosts_and_thieves.entities.Thief;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class ThiefThread implements Runnable {

    private Thief thief;
    private final int permits;
    private Home home;
    private Semaphore semaphore;
    private CyclicBarrier barrier;
    private AtomicInteger counter;


    public ThiefThread(Thief thief, Home home, Semaphore semaphore, int permits, CyclicBarrier barrier, AtomicInteger counter) {
        this.thief = thief;
        this.home = home;
        this.semaphore = semaphore;
        this.permits = permits;
        this.barrier = barrier;
        this.counter = counter;
    }

    @Override
    public void run() {
        try {
            barrier.await();
            while (!home.isEmpty() && !thief.isBagFull()) {
                semaphore.acquire(permits);
                try {
                    counter.incrementAndGet();
                    thief.steal(home);
                    System.out.println("Thieves inside home: " + counter.intValue());
                    counter.decrementAndGet();
                } finally {
                    semaphore.release(permits);
                }
            }
            barrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}

