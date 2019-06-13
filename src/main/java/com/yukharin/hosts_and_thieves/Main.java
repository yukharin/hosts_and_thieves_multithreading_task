package com.yukharin.hosts_and_thieves;

import com.yukharin.hosts_and_thieves.entities.Bag;
import com.yukharin.hosts_and_thieves.entities.Home;
import com.yukharin.hosts_and_thieves.entities.Host;
import com.yukharin.hosts_and_thieves.entities.Thief;
import com.yukharin.hosts_and_thieves.threads.HostThread;
import com.yukharin.hosts_and_thieves.threads.ThiefThread;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;


public class Main {

    // Numeric constants
    private static final int HOSTS = 100;
    private static final int THIEVES = 100;
    private static final int WEIGHT_LIMIT = 100;
    private static final int ITEMS_PER_HOST = 10;
    private static final int TIMEOUT = 3;
    private static final int TOTAL_THREADS = HOSTS + THIEVES;

    // Semaphore, CountDownLatch and AtomicInteger counter
    private static final Semaphore semaphore = new Semaphore(HOSTS);
    private static final AtomicInteger threadsCounter = new AtomicInteger();
    private static final Phaser phaser = new Phaser(TOTAL_THREADS);

    // An Instance of a home class, List of threads and pool of threads
    private static final Home home = new Home();
    private static final List<Runnable> allThreads = new ArrayList<>(TOTAL_THREADS);
    private static final ExecutorService threadPool = Executors.newFixedThreadPool(TOTAL_THREADS);

    // An Instance of a logger to log some useful information
    private static final Logger logger = LogManager.getLogger(Main.class);

    private Main() {
    }

    public static void main(String[] args) throws InterruptedException {

        final long startingTime = System.currentTimeMillis();

        // Initialization
        initThieves(THIEVES);
        initHosts(HOSTS);

        // Printing some statistics before execution
        printStatistics();

        // Sending tasks to thread pool for execution
        for (Runnable runnable : allThreads) {
            threadPool.submit(runnable);
        }

        // Shutting pool down after execution
        threadPool.shutdown();

        // Waiting until all tasks have completed
        threadPool.awaitTermination(TIMEOUT, TimeUnit.MINUTES);

        // Printing some statistics after execution
        printStatistics();

        final long endingTime = System.currentTimeMillis();

        // Printing perfomance of an application
        logger.info("Perfomance: " + (endingTime - startingTime) + " millis");
    }

    private static void initHosts(final int hosts) {
        for (int i = 0; i < hosts; i++) {
            allThreads.add(new HostThread(new Host(ITEMS_PER_HOST), home, semaphore, threadsCounter, phaser));
        }
    }

    private static void initThieves(final int thieves) {
        for (int i = 0; i < THIEVES; i++) {
            allThreads.add(new ThiefThread(new Thief(WEIGHT_LIMIT), home, semaphore, HOSTS, threadsCounter, phaser));
        }
    }

    private static void printStatistics() {
        logger.info("Sum value hosts: " + Host.getSumValue());
        logger.info("Sum weight hosts: " + Host.getSumWeight());
        logger.info("Sum value home: " + home.getSumValue());
        logger.info("Sum weight home: " + home.getSumWeight());
        logger.info("Sum value thieves: " + Bag.getSumValue());
        logger.info("Sum weight thieves: " + Bag.getSumWeight());
    }


}
