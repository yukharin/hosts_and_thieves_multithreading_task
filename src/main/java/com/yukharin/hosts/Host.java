package com.yukharin.hosts;

import com.yukharin.homes.Home;
import com.yukharin.items.Item;
import com.yukharin.utils.Utils;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Host {

    private static AtomicInteger sumValue;
    private static AtomicInteger sumWeight;
    private Home home;
    private List<Item> items;


    static {
        sumWeight = new AtomicInteger();
        sumValue = new AtomicInteger();
    }

    public Host(Home home, int count) {
        this.home = home;
        this.items = Utils.generateItems(count);
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

    public void putItems() {
        Iterator<Item> iterator = items.iterator();
        while (iterator.hasNext()) {
            Item item = iterator.next();
            sumWeight.addAndGet(Math.negateExact(item.getWeight()));
            sumValue.addAndGet(Math.negateExact(item.getValue()));
            home.addItem(item);
            iterator.remove();
        }
    }

    public Home getHome() {
        return this.home;
    }

    public int itemsCount() {
        return items.size();
    }

    @Override
    public String toString() {
        return Thread.currentThread().getName() + " - Host class - items: " + items;
    }


}
