package com.yukharin.hosts_and_thieves.entities;

import com.yukharin.hosts_and_thieves.utils.Utils;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public final class Host {

    private static AtomicInteger sumValue = new AtomicInteger();
    private static AtomicInteger sumWeight = new AtomicInteger();


    private List<Item> items;

    public Host(int count) {
        this.items = Objects.requireNonNull(Utils.generateItems(count));
        generateAggregationValues(items);
    }

    private static void generateAggregationValues(List<Item> items) {
        for (Item item : items) {
            sumValue.addAndGet(item.getValue());
            sumWeight.addAndGet(item.getWeight());
        }
    }

    public static int getSumValue() {
        return sumValue.intValue();
    }

    public static int getSumWeight() {
        return sumWeight.intValue();
    }

    public void putItems(Home home) {
        Objects.requireNonNull(home);
        Iterator<Item> iterator = items.iterator();
        while (iterator.hasNext()) {
            Item item = iterator.next();
            sumWeight.addAndGet(Math.negateExact(item.getWeight()));
            sumValue.addAndGet(Math.negateExact(item.getValue()));
            home.addItem(item);
            iterator.remove();
        }
    }

    public void putItem(Home home) {
        Objects.requireNonNull(home);
        Iterator<Item> iterator = items.iterator();
        if (iterator.hasNext()) {
            Item item = iterator.next();
            sumWeight.addAndGet(Math.negateExact(item.getWeight()));
            sumValue.addAndGet(Math.negateExact(item.getValue()));
            home.addItem(item);
            iterator.remove();
        }
    }

    public boolean haveItems() {
        return !items.isEmpty();
    }


    @Override
    public String toString() {
        return Thread.currentThread().getName() + " - Host class - items: " + items;
    }


}