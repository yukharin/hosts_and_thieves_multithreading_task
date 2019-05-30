package com.yukharin.listeners;

import com.yukharin.items.Item;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Listener {

    private static final AtomicInteger SUM_WEIGHT_HOSTS = new AtomicInteger();
    private static final AtomicInteger SUM_VALUE_HOSTS = new AtomicInteger();
    private static final AtomicInteger SUM_WEIGHT_THIEVES = new AtomicInteger();
    private static final AtomicInteger SUM_VALUE_THIEVES = new AtomicInteger();

    public static void addHostList(List<Item> items) {
        for (Item item : items) {
            SUM_WEIGHT_HOSTS.addAndGet(item.getWeight());
            SUM_VALUE_HOSTS.addAndGet(item.getValue());
        }
    }

    public static void removeHostItem(Item item) {
        SUM_WEIGHT_HOSTS.addAndGet(Math.negateExact(item.getWeight()));
        SUM_VALUE_HOSTS.addAndGet(Math.negateExact(item.getValue()));
    }

    public static void addThiefItem(Item item) {
        SUM_VALUE_THIEVES.addAndGet(item.getValue());
        SUM_WEIGHT_THIEVES.addAndGet(item.getWeight());
    }


    public static int getSumWeightHosts() {
        return SUM_WEIGHT_HOSTS.intValue();
    }

    public static int getSumValueHosts() {
        return SUM_VALUE_HOSTS.intValue();
    }

    public static int getSumWeightThieves() {
        return SUM_WEIGHT_THIEVES.intValue();
    }

    public static int getSumValueThieves() {
        return SUM_VALUE_THIEVES.intValue();
    }


}
