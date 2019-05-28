package com.yukharin.threads;

import com.yukharin.home_types.Home;
import com.yukharin.thief_types.NormalThief;
import com.yukharin.thief_types.Thief;

import java.util.concurrent.TimeUnit;

public class ThiefThread implements Runnable {

    private static final int TIMEOUT = 2;
    private Thief thief;

    public ThiefThread(Home home) {
        this.thief = new NormalThief(home);
    }

    @Override
    public void run() {
        try {
            TimeUnit.SECONDS.sleep(TIMEOUT);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thief.steal();
    }


}
