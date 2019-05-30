package com.yukharin.home_types;

import java.util.Arrays;

public abstract class AbstractHome<T> implements Home<T> {

    protected T[] buffer;
    protected int index;

    protected AbstractHome(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Size can not be less than zero or equals to it.");
        }
        buffer = (T[]) new Object[size];
        index = 0;
    }

    @Override
    public boolean isFull() {
        return index == buffer.length;
    }

    @Override
    public boolean isEmpty() {
        return index == 0;
    }

    @Override
    public String toString() {
        return Arrays.toString(buffer);
    }

}