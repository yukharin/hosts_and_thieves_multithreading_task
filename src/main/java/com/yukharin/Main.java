package com.yukharin;

import com.yukharin.homes.Home;
import com.yukharin.host_threads.HostThread;
import com.yukharin.hosts.Host;
import com.yukharin.thief_threads.ThiefThread;
import com.yukharin.thieves.Thief;
import com.yukharin.utils.Utils;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    private static int HOSTS = 5;
    private static int THIEVES = 5;
    private static int ITEMS_PER_HOST = 3;
    private static int TOTAL_THREADS = HOSTS + THIEVES;
    private static Runnable startTask = new Runnable() {
        @Override
        public void run() {
            System.out.println("Start !!!");
            Utils.printInfo();
        }
    };
    public static CyclicBarrier start = new CyclicBarrier(TOTAL_THREADS, startTask);
    private static Runnable endTask = new Runnable() {
        @Override
        public void run() {
            System.out.println("Finish !!!");
            Utils.printInfo();
        }
    };
    public static CyclicBarrier finish = new CyclicBarrier(TOTAL_THREADS, endTask);

    public static void main(String[] args) {
        Home home = new Home();
        ExecutorService service = Executors.newFixedThreadPool(TOTAL_THREADS);
        for (int i = 0; i < HOSTS; i++) {
            service.submit(new HostThread(new Host(home, ITEMS_PER_HOST), start, finish));
        }
        for (int i = 0; i < THIEVES; i++) {
            service.submit(new ThiefThread(new Thief(home), start, finish));
        }
        service.shutdown();
    }

}
