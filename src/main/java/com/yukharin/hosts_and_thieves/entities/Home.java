package com.yukharin.hosts_and_thieves.entities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public class Home implements Iterable<Item> {

    private final List<Item> items;
    private int sumValue;
    private int sumWeight;
    private static Logger logger = LogManager.getLogger(Home.class);

    public Home() {
        items = Collections.synchronizedList(new ArrayList<>());
    }

    public void addItem(Item item) {
        items.add(Objects.requireNonNull(item));
        logger.info(Thread.currentThread().getName() + " adding item ");
    }

    public void removeItem(Item item) {
        items.remove(item);
        logger.info(Thread.currentThread().getName() + " removing item ");
    }

    public int countItems() {
        return items.size();
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public void updateAggregationValues() {
        this.sumWeight = 0;
        this.sumValue = 0;
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