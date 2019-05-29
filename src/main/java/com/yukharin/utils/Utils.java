package com.yukharin.utils;

import com.yukharin.items.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Utils {

    private Utils() {

    }

    public static List<Item> generateRandomListOfItems(int size) {
        List<Item> items = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            items.add(generateRandomItem());
        }
        return items;
    }

    private static Item generateRandomItem() {
        Item[] items = Item.values();
        Random random = new Random();
        int randomNumber = random.nextInt(items.length);
        return items[randomNumber];
    }

}
