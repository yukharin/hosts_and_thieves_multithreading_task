package com.yukharin.threads;

import com.yukharin.home_types.Home;
import com.yukharin.host_types.Host;
import com.yukharin.host_types.NormalHost;

public class HostThread implements Runnable {

    private Host host;

    public HostThread(Home home, int size) {
        this.host = new NormalHost(home, size);
    }

    @Override
    public void run() {
        System.out.println(host);
        host.put();
        System.out.println(host);
    }

}
