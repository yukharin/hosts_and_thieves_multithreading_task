package com.yukharin.hosts;

import com.yukharin.homes.Home;
import com.yukharin.items.Item;
import com.yukharin.utils.Utils;

import java.util.Iterator;
import java.util.List;

public class Host {

    private static int sumValue;
    private Home home;
    private static int sumWeight;
    private List<Item> items;


    public Host(Home home, int count) {
        this.home = home;
        this.items = Utils.generateItems(count);
        generateAggregationValues(items);
        System.out.println(Thread.currentThread().getName() + " Host class - initialization process " + items);
    }

    private static void generateAggregationValues(List<Item> items) {
        for (Item item : items) {
            sumValue += item.getValue();
            sumWeight += item.getWeight();
        }
    }

    public static int getSumValue() {
        return sumValue;
    }

    public static int getSumWeight() {
        return sumWeight;
    }

    public void putItems() {
        Iterator<Item> iterator = items.iterator();
        while (iterator.hasNext()) {
            Item item = iterator.next();
            sumWeight -= item.getWeight();
            sumValue -= item.getValue();
            home.addItem(item);
            iterator.remove();
        }
    }

    public int itemsCount() {
        return items.size();
    }

    @Override
    public String toString() {
        return Thread.currentThread().getName() + " - Host class - items: " + items;
    }


}
