package com.yukharin.hosts_and_thieves.utils;

import com.yukharin.hosts_and_thieves.entities.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UtilityMethods {



    private static final int LOWER_WEIGHT = 5;
    private static final int UPPER_WEIGHT = 30;
    private static final int LOWER_VALUE = 15;
    private static final int UPPER_VALUE = 150;

    private UtilityMethods() {
    }

    public static List<Item> generateItems(final int count) {
        final List<Item> items = new ArrayList<>(count);
        final Random random = new Random();
        for (int i = 0; i < count; i++) {
            int weight = LOWER_WEIGHT + random.nextInt(UPPER_WEIGHT);
            int value = LOWER_VALUE + random.nextInt(UPPER_VALUE);
            items.add(new Item(weight, value));
        }
        return items;
    }

}