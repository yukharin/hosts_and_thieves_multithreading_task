package com.yukharin.homes;

import com.yukharin.items.Item;

import java.util.Collections;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class Home implements Iterable<Item> {

    private final CopyOnWriteArrayList<Item> items;
    private static Set<Thread> threads = Collections.newSetFromMap(new ConcurrentHashMap<>());
    private static AtomicInteger sumValue = new AtomicInteger();
    private static AtomicInteger sumWeight = new AtomicInteger();

    public Home() {
        items = new CopyOnWriteArrayList<>();
    }

    public static int getSumValue() {
        return sumValue.intValue();
    }

    public static int getSumWeight() {
        return sumWeight.intValue();
    }

    public void addItem(Item item) {
        threads.add(Thread.currentThread());
        items.add(item);
        sumValue.addAndGet(item.getValue());
        sumWeight.addAndGet(item.getWeight());
        System.out.println(threads.size() + " - threads adding items");
        System.out.println(Thread.currentThread().getName() + " - adding items " + this);
        threads.remove(Thread.currentThread());
    }

    public void removeItem(Item item) {
        threads.add(Thread.currentThread());
        sumValue.addAndGet(Math.negateExact(item.getValue()));
        sumWeight.addAndGet(Math.negateExact(item.getWeight()));
        items.remove(item);
        System.out.println(threads.size() + " - threads are removing items");
        System.out.println(Thread.currentThread().getName() + " - removing items " + this);
        threads.remove(Thread.currentThread());
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
