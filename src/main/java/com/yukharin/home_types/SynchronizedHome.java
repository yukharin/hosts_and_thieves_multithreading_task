package com.yukharin.home_types;


import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class SynchronizedHome<T> extends AbstractHome<T> implements Home<T> {


    private static final long TIMEOUT_SECONDS = 2L;
    private Object lock;


    public SynchronizedHome(int size) {
        super(size);
        lock = new Object();
    }


    public void put(T element) {
        synchronized (lock) {
            while (isFull()) {
                try {
                    lock.wait(TimeUnit.SECONDS.toSeconds(TIMEOUT_SECONDS));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            buffer[index] = element;
            index++;
            lock.notifyAll();
        }
    }


    public T get() {
        synchronized (lock) {
            while (isEmpty()) {
                try {
                    lock.wait(TimeUnit.SECONDS.toSeconds(TIMEOUT_SECONDS));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            T element = buffer[index - 1];
            buffer[index - 1] = null;
            index--;
            lock.notifyAll();
            return element;
        }
    }

    public String toString() {
        return Arrays.toString(buffer);
    }
}