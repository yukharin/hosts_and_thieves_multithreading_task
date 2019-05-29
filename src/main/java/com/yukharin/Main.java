package com.yukharin;

import com.yukharin.factories.HostThreadFactory;
import com.yukharin.factories.ThiefThreadFactory;
import com.yukharin.home_types.Home;
import com.yukharin.home_types.ItemsHome;
import com.yukharin.thread_manager.ThreadManager;

import java.util.List;

public class Main {

    private static final int NUMBER_OF_HOSTS = 3;
    private static final int NUMBER_OF_THIEVES = 3;
    private static final int NUMBER_OF_ITEMS_PER_HOST = 5;

    public static void main(String[] args) {

        Home home = new ItemsHome<>();
        List<Thread> hosts = HostThreadFactory.createHostThreads(home, NUMBER_OF_ITEMS_PER_HOST, NUMBER_OF_HOSTS);
        List<Thread> thieves = ThiefThreadFactory.createThiefThreads(home, NUMBER_OF_THIEVES);
        ThreadManager hostManager = new ThreadManager(hosts);
        ThreadManager thievesManager = new ThreadManager(thieves);
        hostManager.startThreads();
        hostManager.joinThreads();
        thievesManager.startThreads();
        thievesManager.joinThreads();
    }

}
