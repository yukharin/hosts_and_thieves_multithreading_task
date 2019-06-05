package com.yukharin.entities;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Bag implements Iterable<Item> {

    private static AtomicInteger sumValue = new AtomicInteger();
    private static AtomicInteger sumWeight = new AtomicInteger();
    private static final int WEIGHT_LIMIT = 100;
    private List<Item> items;
    private int currentWeight;


    public Bag() {
        this.items = new ArrayList<>();
    }

    public static int getSumValue() {
        return sumValue.intValue();
    }

    public static int getSumWeight() {
        return sumWeight.intValue();
    }

    public boolean add(Item item) {
        if (item.getWeight() + currentWeight >= WEIGHT_LIMIT) {
            return false;
        } else {
            items.add(item);
            currentWeight += item.getWeight();
            sumValue.addAndGet(item.getValue());
            sumWeight.addAndGet(item.getWeight());
            return true;
        }
    }

    public List<Item> addAll(List<Item> itemsToSteal) {
        for (Item item : itemsToSteal) {
            if (item.getWeight() + currentWeight >= WEIGHT_LIMIT) {
                break;
            } else {
                items.add(item);
                currentWeight += item.getWeight();
                sumValue.addAndGet(item.getValue());
                sumWeight.addAndGet(item.getWeight());
            }
        }
        return items;
    }


    @Override
    public Iterator<Item> iterator() {
        return items.iterator();
    }

    @Override
    public String toString() {
        return "items " + items.toString() + ", current weight: " + currentWeight;
    }


}
