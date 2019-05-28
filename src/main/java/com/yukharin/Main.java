package com.yukharin;

import com.yukharin.home_types.Home;
import com.yukharin.home_types.ItemsHome;
import com.yukharin.items.Item;
import com.yukharin.threads.HostThread;

public class Main {

    private static final int HOSTS = 10;
    private static final int THIEVES = 10;
    private static final int HOME_CAPACITY = 10;
    private static final int NUMBER_OF_ITEMS_PER_HOST = 5;

    public static void main(String[] args) {

        Home<Item> home = new ItemsHome<>(HOME_CAPACITY);
//        Runnable hostfRunnable = new HostThread(home, NUMBER_OF_ITEMS_PER_HOST);
//        Runnable thiefRunnable = new ThiefThread(home);
//        ThreadManager hosts = new ThreadManager(hostfRunnable, HOSTS);
//        ThreadManager thieves = new ThreadManager(thiefRunnable, THIEVES);
//        hosts.startThreads();
//        hosts.joinThreads();
//        thieves.startThreads();
//        thieves.joinThreads();

        Runnable runnable = new HostThread(home, 5);
        Thread thread = new Thread(runnable);
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

}
