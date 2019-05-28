package com.yukharin;

import com.yukharin.home_types.Home;
import com.yukharin.home_types.ItemsHome;
import com.yukharin.items.Item;
import com.yukharin.thread_manager.ThreadManager;
import com.yukharin.threads.HostThread;
import com.yukharin.threads.ThiefThread;

public class Main {

    private static final int HOSTS = 20;
    private static final int THIEVES = 4;
    private static final int HOME_CAPACITY = 10;
    private static final int NUMBER_OF_ITEMS_PER_HOST = 5;

    public static void main(String[] args) {

        Home<Item> home = new ItemsHome<>(HOME_CAPACITY);
        Runnable hostsRunnable = new HostThread(home, NUMBER_OF_ITEMS_PER_HOST);
        Runnable thievesRunnable = new ThiefThread(home);
        ThreadManager hosts = new ThreadManager();
        ThreadManager thieves = new ThreadManager();
        hosts.createThreads(hostsRunnable, HOSTS);
        thieves.createThreads(thievesRunnable, THIEVES);
        hosts.startThreads();
        thieves.startThreads();
        hosts.joinThreads();
        thieves.joinThreads();
    }

}
