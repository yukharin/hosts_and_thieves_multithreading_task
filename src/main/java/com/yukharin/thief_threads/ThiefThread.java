package com.yukharin.thief_threads;

import com.yukharin.thieves.Thief;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class ThiefThread implements Runnable {

    private Thief thief;
    private CyclicBarrier start;
    private CyclicBarrier finish;

    public ThiefThread(Thief thief, CyclicBarrier start, CyclicBarrier finish) {
        this.thief = thief;
        this.start = start;
        this.finish = finish;
    }

    @Override
    public void run() {
        if (start != null) {
            try {
                start.await();
                synchronized (thief.getHome()) {
                    thief.steal();
                }
                finish.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }
}
