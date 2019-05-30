package com.yukharin.host_threads;

import com.yukharin.hosts.Host;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class HostThread implements Runnable {

    private Host host;
    private CyclicBarrier start;
    private CyclicBarrier finish;


    public HostThread(Host host, CyclicBarrier start, CyclicBarrier finish) {
        this.host = host;
        this.start = start;
        this.finish = finish;
    }

    @Override
    public void run() {
        if (start != null) {
            try {
                start.await();
                while (host.thingsCount() != 0) {
                    host.putElement();
                }
                finish.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

}
