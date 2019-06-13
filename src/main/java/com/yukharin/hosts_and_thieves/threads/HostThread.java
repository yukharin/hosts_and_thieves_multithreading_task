package com.yukharin.hosts_and_thieves.threads;

import com.yukharin.hosts_and_thieves.entities.Home;
import com.yukharin.hosts_and_thieves.entities.Host;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;
import java.util.concurrent.Phaser;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public final class HostThread implements Runnable {

    private final Host host;
    private final Home home;
    private final Semaphore semaphore;
    private final AtomicInteger counter;
    private final Phaser phaser;
    private static final Logger logger = LogManager.getLogger(HostThread.class);

    public HostThread(final Host host, final Home home, final Semaphore semaphore, final AtomicInteger counter, final Phaser phaser) {
        this.host = Objects.requireNonNull(host);
        this.semaphore = Objects.requireNonNull(semaphore);
        this.phaser = Objects.requireNonNull(phaser);
        this.counter = Objects.requireNonNull(counter);
        this.home = Objects.requireNonNull(home);
    }

    @Override
    public void run() {
        try {
            phaser.arriveAndAwaitAdvance();
            putItems();
        } catch (InterruptedException e) {
            logger.warn(e);
        }
    }

    private void putItem() throws InterruptedException {
        while (host.haveItems()) {
            semaphore.acquire();
            try {
                counter.incrementAndGet();
                host.putItem(home);
                logger.info("Hosts inside home: " + counter.intValue());
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
            logger.info("Hosts inside home: " + counter.intValue());
            counter.decrementAndGet();
        } finally {
            semaphore.release();
        }
    }


}
