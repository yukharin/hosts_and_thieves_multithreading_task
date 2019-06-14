package com.yukharin.hosts_and_thieves.threads;

import com.yukharin.hosts_and_thieves.entities.Home;
import com.yukharin.hosts_and_thieves.entities.Host;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;
import java.util.concurrent.Phaser;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;

public class HostLockThread implements Runnable {

    private static final Logger logger = LogManager.getLogger(HostSemaphoreThread.class);
    private final Host host;
    private final Home home;
    private final Lock lock;
    private final AtomicInteger counter;
    private final Phaser phaser;

    public HostLockThread(final Host host, final Home home, final AtomicInteger counter, final Phaser phaser, final Lock lock) {
        this.host = Objects.requireNonNull(host);
        this.lock = Objects.requireNonNull(lock);
        this.phaser = Objects.requireNonNull(phaser);
        this.counter = Objects.requireNonNull(counter);
        this.home = Objects.requireNonNull(home);
    }

    @Override
    public void run() {
        phaser.arriveAndAwaitAdvance();
        putItems();
    }

    private void putItem() {
        while (host.haveItems()) {
            lock.lock();
            try {
                counter.incrementAndGet();
                host.putItem(home);
                logger.info("Hosts inside home: " + counter.intValue());
                counter.decrementAndGet();
            } finally {
                lock.unlock();
            }
        }
    }

    private void putItems() {
        lock.lock();
        try {
            counter.incrementAndGet();
            host.putItems(home);
            logger.info("Hosts inside home: " + counter.intValue());
            counter.decrementAndGet();
        } finally {
            lock.unlock();
        }
    }

}
