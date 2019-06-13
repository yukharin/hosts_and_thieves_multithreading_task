package com.yukharin.hosts_and_thieves.entities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public final class Home implements Iterable<Item> {

    private final List<Item> items;
    private AtomicInteger sumValue;
    private AtomicInteger sumWeight;
    private static Logger logger = LogManager.getLogger(Home.class);

    public Home() {
        items = Collections.synchronizedList(new ArrayList<>());
        sumValue = new AtomicInteger();
        sumWeight = new AtomicInteger();
    }

    public void addItem(Item item) {
        items.add(Objects.requireNonNull(item));
        sumValue.addAndGet(item.getValue());
        sumWeight.addAndGet(item.getWeight());
        logger.info(Thread.currentThread().getName() + " adding item ");
    }

    public void removeItem(Item item) {
        items.remove(item);
        sumValue.addAndGet(Math.negateExact(item.getValue()));
        sumWeight.addAndGet(Math.negateExact(item.getWeight()));
        logger.info(Thread.currentThread().getName() + " removing item ");
    }

    public int countItems() {
        return items.size();
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public int getSumValue() {
        return sumValue.intValue();
    }

    public int getSumWeight() {
        return sumWeight.intValue();
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