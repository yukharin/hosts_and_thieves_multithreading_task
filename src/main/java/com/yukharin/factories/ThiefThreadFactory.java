package com.yukharin.factories;

import com.yukharin.home_types.Home;
import com.yukharin.thief_types.NormalThief;
import com.yukharin.threads.ThiefThread;

import java.util.ArrayList;
import java.util.List;

public class ThiefThreadFactory {

    public static List<Thread> createThiefThreads(final Home home, final int NUMBER_OF_THIEVES) {

        List<Thread> result = new ArrayList<>(NUMBER_OF_THIEVES);
        for (int i = 0; i < NUMBER_OF_THIEVES; i++) {
            Thread thread = new Thread(new ThiefThread(new NormalThief(home)));
            result.add(thread);
        }
        return result;
    }

}
