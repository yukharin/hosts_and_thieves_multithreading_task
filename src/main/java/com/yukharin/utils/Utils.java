package com.yukharin.utils;

import com.yukharin.bags.Bag;
import com.yukharin.homes.Home;
import com.yukharin.hosts.Host;
import com.yukharin.items.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Utils {

    private static final int LOWER_WEIGHT = 2;
    private static final int UPPER_WEIGHT = 16;
    private static final int LOWER_VALUE = 10;
    private static final int UPPER_VALUE = 100;

    public static List<Item> generateItems(int count) {
        List<Item> items = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < count; i++) {
            int weight = LOWER_WEIGHT + random.nextInt(UPPER_WEIGHT);
            int value = LOWER_VALUE + random.nextInt(UPPER_VALUE);
            items.add(new Item(weight, value));
        }
        return items;
    }

    public static void printInfo() {
        System.out.println("Sum value hosts: " + Host.getSumValue());
        System.out.println("Sum weight hosts: " + Host.getSumWeight());
        System.out.println("Sum value home: " + Home.getSumValue());
        System.out.println("Sum weight home: " + Home.getSumWeight());
        System.out.println("Sum value thieves: " + Bag.getSumValue());
        System.out.println("Sum weight thieves: " + Bag.getSumWeight());
    }
}
