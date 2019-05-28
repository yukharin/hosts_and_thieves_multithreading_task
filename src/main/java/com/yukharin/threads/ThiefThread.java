package com.yukharin.threads;

import com.yukharin.home_types.Home;
import com.yukharin.thief_types.NormalThief;
import com.yukharin.thief_types.Thief;

public class ThiefThread implements Runnable {

    private Thief thief;

    public ThiefThread(Home home) {
        this.thief = new NormalThief(home);
    }

    @Override
    public void run() {
        thief.steal();
        System.out.println(thief);
    }


}
