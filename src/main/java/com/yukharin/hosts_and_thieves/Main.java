package com.yukharin.hosts_and_thieves;

import com.yukharin.hosts_and_thieves.entities.Home;
import com.yukharin.hosts_and_thieves.entities.Host;
import com.yukharin.hosts_and_thieves.entities.Thief;
import com.yukharin.hosts_and_thieves.threads.HostThread;
import com.yukharin.hosts_and_thieves.threads.ThiefThread;
import com.yukharin.hosts_and_thieves.utils.Utils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;


public class Main {

    private static final int HOSTS = 100;
    private static final int THIEVES = 100;
    private static final int ITEMS_PER_HOST = 5;
    private static final int TOTAL_THREADS = HOSTS + THIEVES;
    private static final Semaphore semaphore = new Semaphore(HOSTS);
    private static final Home home = new Home();
    private static final AtomicInteger threadsCounter = new AtomicInteger();
    private static final Logger logger = LogManager.getLogger(Main.class);
    private static final int TIMEOUT = 3;
    private static final CountDownLatch latch = new CountDownLatch(TOTAL_THREADS);


    public static void main(String[] args) throws InterruptedException {
        long startingTime = System.currentTimeMillis();
        List<Runnable> threads = new ArrayList<>(TOTAL_THREADS);
        for (int i = 0; i < HOSTS; i++) {
            threads.add(new HostThread(new Host(ITEMS_PER_HOST), home, semaphore, latch, threadsCounter));
        }
        for (int i = 0; i < THIEVES; i++) {
            threads.add(new ThiefThread(new Thief(), home, semaphore, HOSTS, latch, threadsCounter));
        }
        Utils.printInfo(home);
        Collections.shuffle(threads);
        ExecutorService service = Executors.newFixedThreadPool(TOTAL_THREADS);
        for (Runnable runnable : threads) {
            service.submit(runnable);
        }
        service.shutdown();
        service.awaitTermination(TIMEOUT, TimeUnit.SECONDS);
        Utils.printInfo(home);
        long endingTime = System.currentTimeMillis();
        logger.info("Perfomance: " + (endingTime - startingTime) + " millis");
    }

}
