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
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class Main {

    private static final int HOSTS = 50;
    private static final int THIEVES = 30;
    private static final int ITEMS_PER_HOST = 3;
    private static final int TOTAL_THREADS = HOSTS + THIEVES;
    private static final Semaphore semaphore = new Semaphore(HOSTS);

    public static void main(String[] args) throws InterruptedException {
        Home home = new Home();
        ExecutorService service = Executors.newFixedThreadPool(TOTAL_THREADS);
        List<Callable<Void>> threads = new ArrayList<>();
        for (int i = 0; i < HOSTS; i++) {
            threads.add(new HostThread(new Host(home, ITEMS_PER_HOST), semaphore));
        }
        for (int i = 0; i < THIEVES; i++) {
            threads.add(new ThiefThread(new Thief(), home, semaphore, HOSTS));
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
