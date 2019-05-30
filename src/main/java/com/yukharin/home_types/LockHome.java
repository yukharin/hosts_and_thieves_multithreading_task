package com.yukharin.home_types;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockHome<T> extends AbstractHome<T> implements Home<T> {


    Condition notFull;
    Condition notEmpty;
    private Lock lock;

    public LockHome(int size) {
        super(size);
        lock = new ReentrantLock();
        notFull = lock.newCondition();
        notEmpty = lock.newCondition();
    }


    public void put(T element) {
        try {
            lock.lock();
            while (isFull()) {
                try {
                    notFull.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            buffer[index + 1] = element;
            index++;
            notEmpty.signalAll();
        } finally {
            lock.unlock();
        }
    }


    public T get() {
        try {
            lock.lock();
            while (isEmpty()) {
                try {
                    notEmpty.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            T element = buffer[index];
            buffer[index] = null;
            index--;
            notFull.signalAll();
            return element;
        } finally {
            lock.unlock();
        }
    }


}
