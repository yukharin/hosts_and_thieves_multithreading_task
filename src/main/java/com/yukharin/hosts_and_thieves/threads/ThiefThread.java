package com.yukharin.hosts_and_thieves.threads;

import com.yukharin.hosts_and_thieves.entities.Home;
import com.yukharin.hosts_and_thieves.entities.Thief;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;
import java.util.concurrent.Phaser;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public final class ThiefThread implements Runnable {


    private final Thief thief;
    private final Home home;
    private final Semaphore semaphore;
    private final AtomicInteger counter;
    private final int permits;
    private final Phaser phaser;
    private static final Logger logger = LogManager.getLogger(ThiefThread.class);


    public ThiefThread(final Thief thief, final Home home, final Semaphore semaphore, final int permits, final AtomicInteger counter, final Phaser phaser) {
        this.thief = Objects.requireNonNull(thief);
        this.home = Objects.requireNonNull(home);
        this.semaphore = Objects.requireNonNull(semaphore);
        this.counter = Objects.requireNonNull(counter);
        this.phaser = Objects.requireNonNull(phaser);
        this.permits = permits;
    }

    @Override
    public void run() {
        try {
            phaser.arriveAndAwaitAdvance();
            stealAll();
        } catch (InterruptedException e) {
            logger.warn(e);
        }
    }


    private void stealIncrementally() throws InterruptedException {
        while (!home.isEmpty() && !thief.isBagFull()) {
            semaphore.acquire(permits);
            try {
                counter.incrementAndGet();
                thief.steal(home);
                logger.info("Thieves inside home: " + counter.intValue());
                counter.decrementAndGet();
            } finally {
                semaphore.release(permits);
            }
        }
    }

    private void stealAll() throws InterruptedException {
        semaphore.acquire(permits);
        try {
            counter.incrementAndGet();
            thief.stealAll(home);
            logger.info("Thieves inside home: " + counter.intValue());
            counter.decrementAndGet();
        } finally {
            semaphore.release(permits);
        }
    }
}