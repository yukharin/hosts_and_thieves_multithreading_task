package com.yukharin.utils;

import com.yukharin.entities.Bag;
import com.yukharin.entities.Home;
import com.yukharin.entities.Host;
import com.yukharin.entities.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Utils {

    private static final int LOWER_WEIGHT = 15;
    private static final int UPPER_WEIGHT = 50;
    private static final int LOWER_VALUE = 15;
    private static final int UPPER_VALUE = 150;

    public static List<Item> generateItems(int count) {
        List<Item> items = new ArrayList<>(count);
        Random random = new Random();
        for (int i = 0; i < count; i++) {
            int weight = LOWER_WEIGHT + random.nextInt(UPPER_WEIGHT);
            int value = LOWER_VALUE + random.nextInt(UPPER_VALUE);
            items.add(new Item(weight, value));
        }
        return items;
    }

    public static void printInfo(Home home) {
        home.updateAggregationValues();
        System.out.println("Sum value hosts: " + Host.getSumValue());
        System.out.println("Sum weight hosts: " + Host.getSumWeight());
        System.out.println("Sum value home: " + home.getSumValue());
        System.out.println("Sum weight home: " + home.getSumWeight());
        System.out.println("Sum value thieves: " + Bag.getSumValue());
        System.out.println("Sum weight thieves: " + Bag.getSumWeight());
    }
}

