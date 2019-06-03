package com.yukharin.thief_threads;

import com.yukharin.homes.Home;
import com.yukharin.thieves.Thief;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class ThiefThread implements Runnable {

    private Thief thief;
    private CyclicBarrier barrier;
    private final int permits;
    private Home home;
    private Semaphore semaphore;
    private static final long TIMEOUT = 5L;

    public ThiefThread(Thief thief, Home home, CyclicBarrier barrier, Semaphore semaphore, int permits) {
        this.thief = thief;
        this.barrier = barrier;
        this.home = home;
        this.semaphore = semaphore;
        this.permits = permits;
    }

    @Override
    public void run() {
        if (barrier != null) {
            try {
                barrier.await();
                if (semaphore.tryAcquire(permits, TIMEOUT, TimeUnit.SECONDS)) {
                    try {
                        thief.steal(home);
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
}
