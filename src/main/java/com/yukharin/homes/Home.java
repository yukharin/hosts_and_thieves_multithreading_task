package com.yukharin.homes;

import com.yukharin.comparators.ItemComparator;
import com.yukharin.items.Item;

import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class Home implements Iterable<Item> {

    private final CopyOnWriteArrayList<Item> items;
    private static Set<Thread> threads = new HashSet<>();
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
        System.out.println(Thread.currentThread().getName() + " - Home - adding items to home " + this);
        threads.add(Thread.currentThread());
        items.add(item);
        sumValue.addAndGet(item.getValue());
        sumWeight.addAndGet(item.getWeight());
        System.out.println("com.yukharin.home.Home.addItem - threads working here: " + threads.size());
        threads.remove(Thread.currentThread());
    }

    public void removeItem(Item item) {
        System.out.println(Thread.currentThread().getName() + " - Home - removing items from home " + this);
        threads.add(Thread.currentThread());
        sumValue.addAndGet(Math.negateExact(item.getValue()));
        sumWeight.addAndGet(Math.negateExact(item.getWeight()));
        items.remove(item);
        System.out.println("com.yukharin.home.Home.removeItem - threads removing here: " + threads.size());
        threads.remove(Thread.currentThread());
    }

    @Override
    public Iterator<Item> iterator() {
        return items.iterator();
    }

    public void sortItems() {
        items.sort(new ItemComparator().reversed());
    }

    public void shuffleItems() {
        Collections.shuffle(items);
    }

    @Override
    public String toString() {
        return items.toString();
    }

}
