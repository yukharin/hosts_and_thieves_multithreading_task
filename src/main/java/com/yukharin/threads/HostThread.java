package com.yukharin.threads;

import com.yukharin.host_types.Host;

import java.util.concurrent.TimeUnit;

public class HostThread implements Runnable {

    private Host host;
    private static final int TIMEOUT = 1;

    public HostThread(Host host) {
        this.host = host;
    }

    @Override
    public void run() {
        try {
            TimeUnit.SECONDS.sleep(TIMEOUT);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        host.put();
    }

}
