package com.yukharin.utils;

import com.yukharin.items.Item;
import com.yukharin.listeners.Listener;

import java.util.LinkedList;
import java.util.concurrent.ThreadLocalRandom;

public class Utils {

    private static final int LOWER_WEIGHT = 2;
    private static final int UPPER_WEIGHT = 16;
    private static final int LOWER_VALUE = 10;
    private static final int UPPER_VALUE = 100;

    public static LinkedList<Item> generateItems(int count) {
        LinkedList<Item> items = new LinkedList<>();
        for (int i = 0; i < count; i++) {
            int weight = ThreadLocalRandom.current().nextInt(LOWER_WEIGHT, UPPER_WEIGHT);
            int value = ThreadLocalRandom.current().nextInt(LOWER_VALUE, UPPER_VALUE);
            Item item = new Item(weight, value);
            items.add(item);
        }
        return items;
    }

    public static void printInfo() {
        System.out.println("Sum value hosts: " + Listener.getSumValueHosts());
        System.out.println("Sum weight hosts: " + Listener.getSumWeightHosts());
        System.out.println("Sum value thieves: " + Listener.getSumValueThieves());
        System.out.println("Sum weight thieves: " + Listener.getSumWeightThieves());
    }
}
