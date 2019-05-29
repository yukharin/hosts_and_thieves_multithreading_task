package com.yukharin.factories;

import com.yukharin.home_types.Home;
import com.yukharin.host_types.NormalHost;
import com.yukharin.threads.HostThread;

import java.util.ArrayList;
import java.util.List;

public class HostThreadFactory {

    public static List<Thread> createHostThreads(final Home home, final int ITEMS_PER_HOST, final int NUMBER_OF_HOSTS) {

        List<Thread> result = new ArrayList<>(NUMBER_OF_HOSTS);
        for (int i = 0; i < NUMBER_OF_HOSTS; i++) {
            Thread thread = new Thread(new HostThread(new NormalHost(home, ITEMS_PER_HOST)));
            result.add(thread);
        }
        return result;
    }
}


