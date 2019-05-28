package com.yukharin.thread_manager;

import java.util.ArrayList;
import java.util.List;

public class ThreadManager {


    private List<Thread> threadList;

    public ThreadManager() {
        threadList = new ArrayList<>();
    }

    public void createThreads(Runnable runnable, int numberOfThreads) {
        for (int i = 0; i < numberOfThreads; i++) {
            threadList.add(new Thread(runnable));
        }
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
