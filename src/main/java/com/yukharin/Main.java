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

    private static final int HOSTS = 150;
    private static final int THIEVES = 300;
    private static final int ITEMS_PER_HOST = 3;
    private static final int TOTAL_THREADS = HOSTS + THIEVES;
    private static final Semaphore semaphore = new Semaphore(HOSTS);
    private static final CyclicBarrier barrier = new CyclicBarrier(TOTAL_THREADS);

    public static void main(String[] args) throws InterruptedException {
        Home home = new Home();
        ExecutorService service = Executors.newFixedThreadPool(TOTAL_THREADS);
        List<Callable<Void>> threads = new ArrayList<>();

        for (int i = 0; i < HOSTS; i++) {
            threads.add(new HostThread(new Host(home, ITEMS_PER_HOST), barrier, semaphore));
        }
        for (int i = 0; i < THIEVES; i++) {
            threads.add(new ThiefThread(new Thief(), home, barrier, semaphore, HOSTS));
        }
        Collections.shuffle(threads);
        System.out.println("Start: ");
        Utils.printInfo();
        service.invokeAll(threads);
        System.out.println("Finish: ");
        Utils.printInfo();
        service.shutdown();
    }

}
