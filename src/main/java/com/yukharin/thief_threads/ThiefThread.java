package com.yukharin.thief_threads;

import com.yukharin.homes.Home;
import com.yukharin.thieves.Thief;

import java.util.concurrent.Callable;
import java.util.concurrent.Semaphore;

public class ThiefThread implements Callable<Void> {

    private Thief thief;
    private final int permits;
    private Home home;
    private Semaphore semaphore;

    public ThiefThread(Thief thief, Home home, Semaphore semaphore, int permits) {
        this.thief = thief;
        this.home = home;
        this.semaphore = semaphore;
        this.permits = permits;
    }

    @Override
    public Void call() {
        if (semaphore.tryAcquire(permits)) {
            try {
                thief.steal(home);
            } finally {
                semaphore.release(permits);
            }
        }
        return null;
    }
}

