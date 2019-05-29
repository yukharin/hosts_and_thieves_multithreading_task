package com.yukharin.threads;

import com.yukharin.thief_types.Thief;

import java.util.concurrent.TimeUnit;

public class ThiefThread implements Runnable {

    private static final int TIMEOUT = 2;
    private Thief thief;

    public ThiefThread(Thief thief) {
        this.thief = thief;
    }

    @Override
    public void run() {
        try {
            TimeUnit.SECONDS.sleep(TIMEOUT);
            synchronized (thief.getHomeToSteal()) {
                thief.smartSteal();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}



