package com.yukharin.thief_threads;

import com.yukharin.homes.Home;
import com.yukharin.thieves.Thief;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.Callable;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class ThiefThread implements Callable<Void> {

    private Thief thief;
    private final int permits;
    private Home home;
    private Semaphore semaphore;
    private CyclicBarrier barrier;


    public ThiefThread(Thief thief, Home home, Semaphore semaphore, int permits, CyclicBarrier barrier) {
        this.thief = thief;
        this.home = home;
        this.semaphore = semaphore;
        this.permits = permits;
        this.barrier = barrier;
    }

    @Override
    public Void call() {
        try {
            barrier.await();
            semaphore.acquire(permits);
            try {
                thief.steal(home);
            } finally {
                semaphore.release(permits);
            }
            barrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
        return null;
    }
}

