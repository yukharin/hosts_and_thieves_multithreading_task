package com.yukharin.hosts_and_thieves.threads;

import com.yukharin.hosts_and_thieves.entities.Home;
import com.yukharin.hosts_and_thieves.entities.Thief;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;
import java.util.concurrent.Phaser;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;

public class ThiefLockThread implements Runnable {

    private static final Logger logger = LogManager.getLogger(ThiefSemaphoreThread.class);
    private final Thief thief;
    private final Home home;
    private final Lock lock;
    private final AtomicInteger counter;
    private final Phaser phaser;


    public ThiefLockThread(final Thief thief, final Home home, final AtomicInteger counter, final Phaser phaser, final Lock lock) {
        this.thief = Objects.requireNonNull(thief);
        this.home = Objects.requireNonNull(home);
        this.lock = Objects.requireNonNull(lock);
        this.counter = Objects.requireNonNull(counter);
        this.phaser = Objects.requireNonNull(phaser);
    }

    @Override
    public void run() {
        phaser.arriveAndAwaitAdvance();
        stealAll();
    }


    private void stealIncrementally() {
        while (!home.isEmpty() && !thief.isBagFull()) {
            lock.lock();
            try {
                counter.incrementAndGet();
                thief.steal(home);
                logger.info("Thieves inside home: " + counter.intValue());
                counter.decrementAndGet();
            } finally {
                lock.unlock();
            }
        }
    }

    private void stealAll() {
        lock.lock();
        try {
            counter.incrementAndGet();
            thief.stealAll(home);
            logger.info("Thieves inside home: " + counter.intValue());
            counter.decrementAndGet();
        } finally {
            lock.unlock();
        }
    }


}
