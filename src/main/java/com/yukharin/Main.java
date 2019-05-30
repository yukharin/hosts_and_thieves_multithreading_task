package com.yukharin;

import com.yukharin.factories.HostThreadFactory;
import com.yukharin.factories.ThiefThreadFactory;
import com.yukharin.home_types.Home;
import com.yukharin.home_types.SynchronizedHome;
import com.yukharin.items.Item;
import com.yukharin.thread_manager.ThreadManager;

import java.util.List;

public class Main {

    private static final int HOME_CAPACITY = 100;
    private static final int NUMBER_OF_HOSTS = 20;
    private static final int NUMBER_OF_THIEVES = 10;
    private static final int NUMBER_OF_ITEMS_PER_HOST = 5;

    public static void main(String[] args) {

        Home<Item> home = new SynchronizedHome<>(HOME_CAPACITY);
        List<Thread> hosts = HostThreadFactory.createHostThreads(home, NUMBER_OF_ITEMS_PER_HOST, NUMBER_OF_HOSTS);
        List<Thread> thieves = ThiefThreadFactory.createThiefThreads(home, NUMBER_OF_THIEVES);
        ThreadManager thievesManager = new ThreadManager(thieves);
        ThreadManager hostManager = new ThreadManager(hosts);
        thievesManager.startThreads();
        hostManager.startThreads();
        hostManager.joinThreads();
        thievesManager.joinThreads();

    }

}
