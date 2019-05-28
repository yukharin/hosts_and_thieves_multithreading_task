package com.yukharin.home_types;


import java.util.ArrayList;
import java.util.Random;

public class ItemsHome<T> extends AbstractHome<T> implements Home<T> {

    private Object lock;


    public ItemsHome(int capacity) {
        this.elements = new ArrayList<>(capacity);
    }

    @Override
    public synchronized void put(T element) {
        elements.add(element);
    }


    @Override
    public synchronized T get() {
        while (elements.isEmpty()) {
            try {
                lock.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Random random = new Random();
        int index = random.nextInt(elements.size());
        T element = elements.get(index);
        elements.set(index, null);
        return element;
    }

    @Override
    public String toString() {
        return this.elements.toString();
    }

}
