package com.yukharin.hosts_and_thieves;

import com.yukharin.hosts_and_thieves.entities.Home;
import com.yukharin.hosts_and_thieves.entities.Host;
import com.yukharin.hosts_and_thieves.entities.Thief;
import com.yukharin.hosts_and_thieves.threads.HostThread;
import com.yukharin.hosts_and_thieves.threads.ThiefThread;
import com.yukharin.hosts_and_thieves.utils.Utils;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {

    private static final int HOSTS = 100;
    private static final int THIEVES = 100;
    private static final int ITEMS_PER_HOST = 100;
    private static final int TOTAL_THREADS = HOSTS + THIEVES;
    private static final Semaphore semaphore = new Semaphore(HOSTS);
    private static final Home home = new Home();
    private static final Runnable task = () ->
            Utils.printInfo(home);
    private static final CyclicBarrier barrier = new CyclicBarrier(TOTAL_THREADS, task);
    private static final AtomicInteger threadsCounter = new AtomicInteger();


    public static void main(String[] args) throws InterruptedException {
        long startingTime = System.currentTimeMillis();
        System.out.println("Starting time: " + startingTime);
        ExecutorService service = Executors.newFixedThreadPool(TOTAL_THREADS);
        for (int i = 0; i < HOSTS; i++) {
            service.submit(new HostThread(new Host(ITEMS_PER_HOST), home, semaphore, barrier, threadsCounter));
        }
        for (int i = 0; i < THIEVES; i++) {
            service.submit(new ThiefThread(new Thief(), home, semaphore, HOSTS, barrier, threadsCounter));
        }
        service.shutdown();
        service.awaitTermination(3, TimeUnit.MINUTES);
        long endingTime = System.currentTimeMillis();
        System.out.println("Ending time: " + endingTime);
        System.out.println("Perfomance: " + (endingTime - startingTime) + " millis");
    }
    /* Performance before #1: 72 , 85 , 72, 90 , 78 seconds */
    /* Performance after #1: 85 , 87 , 86, 84 , 71, 84 seconds */
    /* Performance before #2: 55 , 55 , 53, 55 , 55 seconds */
    /* Performance after #2: 54 , 54 , 53, 52 , 52, 53 seconds */


    /* Parameters ( HOSTS - 400, THIEVES - 400, ITEMS - 500) */
    /* Performance before #3: 77 , 73.6 , 74, 73.3 , 73.4 seconds */
    /* Performance after #3: 73.5 , 72.6 , 72.3, 73.4 , 72.3, 72.7 seconds */

    /* Performance LinkedList in Utils.generateItems(): 79 , 78.7 , 77.2, 77.7 , 79.4 seconds */
    /* Performance ArrayList (with passed value to the constructor) in Utils.generateItems(): 76.8 , 74 , 72.6, 73.6 , 73.7, 74 seconds */

    /* Performance LinkedList in Utils.generateItems() and in Thief.steal(): 86.6 , 88.3 , 84.3, 87.7 , 90 seconds */
    /* Performance ArrayList (with passed value to the constructor) in Utils.generateItems() and in Thief.steal(): 74.7 , 74 , 74.5, 73.6  74.2 , 74 seconds */

    /* !!!! */
    /* Performance CopyOnWriteArrayList in Home class: 74.7, 73.7, 75.8, 74.7 , 74.9 , 77 , 75.2  seconds */
    /* Performance Synchronized ArrayList in Home class: 15.7, 16, 16, 16.2 , 15.9 , 15.8, 15.9  seconds */
    /* Performance Synchronized LinkedList in Home class: 9 , 16.2, 17.1, 8 , 17.3 , 16.8 , 12.2  seconds */
    /* !!!! */


}
