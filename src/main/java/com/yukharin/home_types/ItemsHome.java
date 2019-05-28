package com.yukharin.home_types;


import java.util.ArrayList;
import java.util.Random;

public class ItemsHome<T> extends AbstractHome<T> implements Home<T> {

    private Object lock;


    public ItemsHome(int capacity) {
        this.elements = new ArrayList<>(capacity);
        lock = new Object();
    }

    @Override
    public void put(T element) {

        synchronized (lock) {
            elements.add(element);
        }
    }


    @Override
    public T get() {
        synchronized (lock) {
            if (elements.size() == 0) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Random random = new Random();
            int index = random.nextInt(elements.size());
            T element = elements.get(index);
            elements.remove(index);
            lock.notifyAll();
            return element;
        }
    }

    public synchronized String toString() {
        return this.elements.toString();
    }

}
