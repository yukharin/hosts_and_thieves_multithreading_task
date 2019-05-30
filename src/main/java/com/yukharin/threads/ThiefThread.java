package com.yukharin.threads;

import com.yukharin.thief_types.Thief;

public class ThiefThread implements Runnable {

    private static final int TIMEOUT = 2;
    private Thief thief;

    public ThiefThread(Thief thief) {
        this.thief = thief;
    }

    @Override
    public void run() {
        thief.steal();
    }
}



