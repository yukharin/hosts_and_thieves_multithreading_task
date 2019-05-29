package com.yukharin.home_types;


import java.util.List;

public interface Home<T> {

    void put(T element);

    T get(int index);

    T getRandomElement();

    void sortElements();

    List<T> getAllElements();

}
