package com.yukharin.bags;

import com.yukharin.items.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Bag {


    private static final int WEIGHT_LIMIT = 100;
    private List<Item> items;
    private int currentWeight;

    public Bag() {
        items = new ArrayList<>();
    }

    public boolean add(Item item) {
        if (item.getWeight() + currentWeight > WEIGHT_LIMIT) {
            return false;
        } else {
            items.add(item);
            currentWeight += item.getWeight();
            return true;
        }
    }

    public Item pickRandomItem() {
        int size = items.size();
        if (size > 0) {
            Random random = new Random();
            int randomNumber = random.nextInt(size);
            Item randomItem = items.get(randomNumber);
            currentWeight = currentWeight - randomItem.getWeight();
            return randomItem;
        } else return null;
    }


    public String toString() {
        return items.toString();
    }

}
