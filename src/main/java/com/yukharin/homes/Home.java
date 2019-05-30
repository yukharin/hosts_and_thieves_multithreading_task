package com.yukharin.homes;

import com.yukharin.items.Item;

import java.util.concurrent.CopyOnWriteArrayList;

public class Home {

    private final CopyOnWriteArrayList<Item> items;
    private int sum_home_weight;
    private int sum_home_value;

    public Home() {
        items = new CopyOnWriteArrayList<>();
    }

    public void add(Item item) {
        items.add(item);
        System.out.println(this);
    }

    public CopyOnWriteArrayList<Item> getItems() {
        return items;
    }

    public void updateAggregationValues() {
        for (Item item : items) {
            sum_home_value += item.getValue();
            sum_home_weight += item.getWeight();
        }
    }

    public int getSum_home_weight() {
        return sum_home_weight;
    }

    public int getSum_home_value() {
        return sum_home_value;
    }

    @Override
    public String toString() {
        return Thread.currentThread().getName() + " - Home class - items: " + items;
    }

}
