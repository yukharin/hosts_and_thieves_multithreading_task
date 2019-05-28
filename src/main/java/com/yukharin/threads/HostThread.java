package com.yukharin.threads;

import com.yukharin.home_types.Home;
import com.yukharin.host_types.Host;
import com.yukharin.host_types.NormalHost;

import java.util.concurrent.TimeUnit;

public class HostThread implements Runnable {

    private Host host;
    private static final int TIMEOUT = 1;

    public HostThread(Home home, int size) {
        this.host = new NormalHost(home, size);
    }

    @Override
    public void run() {
        System.out.println("host: " + host);
        try {
            TimeUnit.SECONDS.sleep(TIMEOUT);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        host.put();
    }

}
