package com.yukharin.hosts_and_thieves.entities;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
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

    public void add(Item item) {
        Objects.requireNonNull(item);
        if (!isEnough(item)) {
            throw new IllegalArgumentException("Item is too heavy for this bag");
        }
        items.add(item);
        currentWeight += item.getWeight();
        sumValue.addAndGet(item.getValue());
        sumWeight.addAndGet(item.getWeight());
    }

    public void addAll(List<Item> itemsToSteal) {
        Objects.requireNonNull(itemsToSteal);
        for (Item item : itemsToSteal) {
            if (!isEnough(item)) {
                throw new IllegalArgumentException("Item is too heavy for this bag");
            }
            items.add(item);
            currentWeight += item.getWeight();
            sumValue.addAndGet(item.getValue());
            sumWeight.addAndGet(item.getWeight());
        }
    }

    public boolean isEnough(Item item) {
        Objects.requireNonNull(item);
        return !(item.getWeight() + currentWeight >= WEIGHT_LIMIT);
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
