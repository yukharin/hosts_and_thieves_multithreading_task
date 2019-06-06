package com.yukharin.hosts_and_thieves.entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Home implements Iterable<Item> {

    private final List<Item> items;
    private int sumValue;
    private int sumWeight;

    public Home() {
        items = Collections.synchronizedList(new ArrayList<>());
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void removeItem(Item item) {
        items.remove(item);
    }

    public int countItems() {
        return items.size();
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public void updateAggregationValues() {
        for (Item item : items) {
            sumWeight += item.getWeight();
            sumValue += item.getValue();
        }
    }

    public int getSumValue() {
        return sumValue;
    }

    public int getSumWeight() {
        return sumWeight;
    }

    @Override
    public Iterator<Item> iterator() {
        return items.iterator();
    }

    @Override
    public String toString() {
        return items.toString();
    }

}
