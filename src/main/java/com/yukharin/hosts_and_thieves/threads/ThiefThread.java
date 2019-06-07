package com.yukharin.hosts_and_thieves.threads;

import com.yukharin.hosts_and_thieves.entities.Home;
import com.yukharin.hosts_and_thieves.entities.Thief;

import java.util.Objects;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ThiefThread implements Runnable {

    private Thief thief;
    private final int permits;
    private Home home;
    private Semaphore semaphore;
    private CyclicBarrier barrier;
    private AtomicInteger counter;


    public ThiefThread(Thief thief, Home home, Semaphore semaphore, int permits, CyclicBarrier barrier, AtomicInteger counter) {
        this.thief = Objects.requireNonNull(thief);
        this.home = Objects.requireNonNull(home);
        this.semaphore = Objects.requireNonNull(semaphore);
        this.barrier = Objects.requireNonNull(barrier);
        this.counter = Objects.requireNonNull(counter);
        this.permits = permits;
    }

    @Override
    public void run() {
        try {
            barrier.await();
            stealIncrementally();
            barrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }


    private void stealIncrementally() throws InterruptedException {
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
            // Без этого таймаута, они друг за другом все равно работают.
            // То есть типо выходят из дома, но потом кто вышел , тот и назад заходит первым.
            // Поставил его тут и написал этот коммент, чтобы вы видели, что я понимаю, что происходит.
            TimeUnit.MILLISECONDS.sleep(1);
        }
    }

    private void stealAll() throws InterruptedException {
        semaphore.acquire(permits);
        try {
            counter.incrementAndGet();
            thief.stealAll(home);
            System.out.println("Thieves inside home: " + counter.intValue());
            counter.decrementAndGet();
        } finally {
            semaphore.release(permits);
        }
    }
}

