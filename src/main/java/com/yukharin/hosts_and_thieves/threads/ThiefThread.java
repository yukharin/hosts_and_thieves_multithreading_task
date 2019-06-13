package com.yukharin.hosts_and_thieves.threads;

import com.yukharin.hosts_and_thieves.entities.Home;
import com.yukharin.hosts_and_thieves.entities.Thief;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class ThiefThread implements Runnable {

    private final int permits;
    private Thief thief;
    private Home home;
    private Semaphore semaphore;
    private CountDownLatch latch;
    private AtomicInteger counter;
    private static Logger logger = LogManager.getLogger(ThiefThread.class);


    public ThiefThread(Thief thief, Home home, Semaphore semaphore, int permits, CountDownLatch latch, AtomicInteger counter) {
        this.thief = Objects.requireNonNull(thief);
        this.home = Objects.requireNonNull(home);
        this.semaphore = Objects.requireNonNull(semaphore);
        this.latch = Objects.requireNonNull(latch);
        this.counter = Objects.requireNonNull(counter);
        this.permits = permits;
    }

    @Override
    public void run() {
        try {
            latch.countDown();
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