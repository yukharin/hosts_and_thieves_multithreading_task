package com.yukharin.hosts_and_thieves;

import com.yukharin.hosts_and_thieves.entities.Bag;
import com.yukharin.hosts_and_thieves.entities.Home;
import com.yukharin.hosts_and_thieves.entities.Host;
import com.yukharin.hosts_and_thieves.entities.Thief;
import com.yukharin.hosts_and_thieves.threads.HostLockThread;
import com.yukharin.hosts_and_thieves.threads.ThiefLockThread;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;


public class Main {

    // Some useful properties variables
    private static final String PROPERTIES_PATH = "config.properties";
    private static final Properties properties = new Properties();

    // Default properties
    private static final String hosts_default = "100";
    private static final String thieves_default = "100";
    private static final String weight_limit_default = "100";
    private static final String items_per_host_default = "5";
    private static final String program_termination_timeout_minutes_default = "3";

    // Properties
    private static int hosts;
    private static int thieves;
    private static int weight_limit;
    private static int items_per_host;
    private static int program_termination_timeout_minutes;
    private static int total_threads;

    // Phaser and AtomicInteger counter
    private static AtomicInteger threadsCounter;
    private static Phaser phaser;

    // Semaphore and ReadWriteLock
    // Make a choice depends on the synchronization type you want
    private static Semaphore semaphore;
    private static ReadWriteLock lock;
    private static Lock lockHost;
    private static Lock lockThief;

    // An Instance of a Home class, List of threads and pool of threads
    private static Home home;
    private static List<Runnable> allThreads;
    private static ExecutorService threadPool;

    // An Instance of a Logger to log some useful information
    private static final Logger logger = LogManager.getLogger(Main.class);

    private Main() {
    }

    public static void main(String[] args) throws InterruptedException {
        final long startingTime = System.currentTimeMillis();

        // Initialization
        initializeProperties();
        initializeObjects();
        initThieves(thieves);
        initHosts(hosts);

        // Printing some statistics before execution
        printStatistics();

        // Sending tasks to thread pool for execution
        for (Runnable runnable : allThreads) {
            threadPool.submit(runnable);
        }

        // Shutting pool down after execution
        threadPool.shutdown();

        // Waiting until all tasks have completed
        threadPool.awaitTermination(program_termination_timeout_minutes, TimeUnit.MINUTES);

        // Printing some statistics after execution
        printStatistics();

        final long endingTime = System.currentTimeMillis();

        // Printing performance of an application
        logger.info("Performance: " + (endingTime - startingTime) + " millis");
    }

    private static void initializeProperties() {
        try {
            properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(PROPERTIES_PATH));
            hosts = Integer.valueOf(properties.getProperty("hosts", hosts_default));
            thieves = Integer.valueOf(properties.getProperty("thieves", thieves_default));
            weight_limit = Integer.valueOf(properties.getProperty("weight_limit", weight_limit_default));
            items_per_host = Integer.valueOf(properties.getProperty("items_per_host", items_per_host_default));
            program_termination_timeout_minutes = Integer.valueOf(properties.getProperty("program_termination_timeout_minutes", program_termination_timeout_minutes_default));
            total_threads = hosts + thieves;
        } catch (IOException e) {
            logger.warn(e);
        }
    }

    private static void initializeObjects() {
        threadsCounter = new AtomicInteger();
        phaser = new Phaser(total_threads);

        semaphore = new Semaphore(hosts);
        lock = new ReentrantReadWriteLock();
        lockHost = lock.readLock();
        lockThief = lock.writeLock();

        home = new Home();
        allThreads = new ArrayList<>(total_threads);
        threadPool = Executors.newFixedThreadPool(total_threads);
    }


    private static void initHosts(final int hosts) {
        for (int i = 0; i < hosts; i++) {
            allThreads.add(new HostLockThread(new Host(items_per_host), home, threadsCounter, phaser, lockHost));
        }
    }

    private static void initThieves(final int thieves) {
        for (int i = 0; i < thieves; i++) {
            allThreads.add(new ThiefLockThread(new Thief(weight_limit), home, threadsCounter, phaser, lockThief));
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
