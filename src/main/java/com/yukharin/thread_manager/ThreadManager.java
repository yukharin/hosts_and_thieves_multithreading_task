package com.yukharin.thread_manager;

import com.yukharin.home_types.Home;
import com.yukharin.home_types.ItemsHome;
import com.yukharin.items.Item;
import com.yukharin.threads.HostThread;

import java.util.ArrayList;
import java.util.List;

public class ThreadManager {


    List<Thread> threadList;

    public ThreadManager(Runnable runnable, int number) {
        this.threadList = new ArrayList<>(number);
        for (int i = 0; i < threadList.size(); i++) {
            threadList.add(new Thread(runnable));
        }
    }

    public static void main(String[] args) {
        Home<Item> home = new ItemsHome<>(10);
        Runnable hostfRunnable = new HostThread(home, 10);
        ThreadManager threadManager = new ThreadManager(hostfRunnable, 20);
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
