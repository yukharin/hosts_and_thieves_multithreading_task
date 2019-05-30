package com.yukharin.bags;

import com.yukharin.items.Item;

import java.util.ArrayList;
import java.util.List;

public class Bag {

    private static final int WEIGHT_LIMIT = 100;
    private List<Item> items;
    private int currentWeight;

    public Bag() {
        items = new ArrayList<>();
    }

    public boolean add(Item item) {
        if (item.getWeight() + currentWeight >= WEIGHT_LIMIT) {
            return false;
        } else {
            items.add(item);
            currentWeight += item.getWeight();
            return true;
        }
    }

    public int count() {
        return items.size();
    }


    public String toString() {
        return "items " + items.toString() + ", current weight: " + currentWeight;
    }

}
