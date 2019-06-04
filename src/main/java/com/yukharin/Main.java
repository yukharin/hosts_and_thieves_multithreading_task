package com.yukharin;

import com.yukharin.homes.Home;
import com.yukharin.host_threads.HostThread;
import com.yukharin.hosts.Host;
import com.yukharin.thief_threads.ThiefThread;
import com.yukharin.thieves.Thief;
import com.yukharin.utils.Utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;

public class Main {

    private static final int HOSTS = 200;
    private static final int THIEVES = 100;
    private static final int ITEMS_PER_HOST = 3;
    private static final int TOTAL_THREADS = HOSTS + THIEVES;
    private static final Semaphore semaphore = new Semaphore(HOSTS);
    private static final Runnable task = () ->
            Utils.printInfo();
    private static final CyclicBarrier barrier = new CyclicBarrier(TOTAL_THREADS, task);


    public static void main(String[] args) throws InterruptedException {
        Home home = new Home();
        ExecutorService service = Executors.newFixedThreadPool(TOTAL_THREADS);
        List<Callable<Void>> threads = new ArrayList<>();
        for (int i = 0; i < HOSTS; i++) {
            threads.add(new HostThread(new Host(home, ITEMS_PER_HOST), semaphore, barrier));
        }
        for (int i = 0; i < THIEVES; i++) {
            threads.add(new ThiefThread(new Thief(), home, semaphore, HOSTS, barrier));
        }
        Collections.shuffle(threads);
        service.invokeAll(threads);
        service.shutdown();
    }

}
