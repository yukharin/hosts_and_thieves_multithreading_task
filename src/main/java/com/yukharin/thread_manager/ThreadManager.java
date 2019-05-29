package com.yukharin.thread_manager;

import java.util.List;

public class ThreadManager {

    private List<Thread> threadList;

    public ThreadManager(final List<Thread> threads) {
        threadList = threads;
    }


    public void startThreads() {
        for (Thread thread : threadList) {
            thread.start();
        }
    }

    public void joinThreads() {
        for (Thread thread : threadList) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
