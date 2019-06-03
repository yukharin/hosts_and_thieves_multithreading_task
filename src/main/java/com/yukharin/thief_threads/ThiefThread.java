package com.yukharin.thief_threads;

import com.yukharin.homes.Home;
import com.yukharin.thieves.Thief;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class ThiefThread implements Runnable {

    private Thief thief;
    private CyclicBarrier start;
    private CyclicBarrier finish;
    private final int permits;
    private Home home;
    private Semaphore semaphore;

    public ThiefThread(Thief thief, Home home, CyclicBarrier start, CyclicBarrier finish, Semaphore semaphore, int permits) {
        this.thief = thief;
        this.start = start;
        this.finish = finish;
        this.home = home;
        this.semaphore = semaphore;
        this.permits = permits;
    }

    @Override
    public void run() {
        if (start != null) {
            try {
                start.await();
                semaphore.acquire(permits);
                thief.steal(home);
                semaphore.release(permits);
                finish.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }
}
