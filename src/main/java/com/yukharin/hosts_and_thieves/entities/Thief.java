package com.yukharin.hosts_and_thieves.entities;

import com.yukharin.hosts_and_thieves.comparators.ItemComparator;

import java.util.*;

public class Thief {

    private static final Comparator<Item> itemComparator = new ItemComparator().reversed();
    private Bag bag;
    private boolean isFull;

    public Thief() {
        this.bag = new Bag();
    }

    public void stealAll(Home home) {
        Objects.requireNonNull(home);
        List<Item> allItems = getSortedItems(home);
        putAndRemoveItems(home, allItems);
    }

    public void steal(Home home) {
        Objects.requireNonNull(home);
        Item item = getMostValuable(home);
        putAndRemoveItem(home, item);
    }

    private void putAndRemoveItem(Home home, Item item) {
        if (bag.isEnough(item)) {
            bag.add(item);
            home.removeItem(item);
        } else {
            markBagFull();
        }
    }

    private void putAndRemoveItems(Home home, List<Item> allItems) {
        for (Item item : allItems) {
            if (bag.isEnough(item)) {
                bag.add(item);
                home.removeItem(item);
            } else {
                markBagFull();
                break;
            }
        }
    }

    private Item getMostValuable(Home home) {
        Item maxItem = new Item(0, 0);
        Iterator<Item> iterator = home.iterator();
        while (iterator.hasNext()) {
            Item item = iterator.next();
            if ((maxItem.compareTo(item)) < 0) {
                maxItem = item;
            }
        }
        return maxItem;
    }

    private List<Item> getSortedItems(Home home) {
        ArrayList<Item> sortedItems = new ArrayList<>(home.countItems());
        Iterator<Item> iterator = home.iterator();
        while (iterator.hasNext()) {
            sortedItems.add(iterator.next());
        }
        sortedItems.sort(itemComparator);
        return sortedItems;
    }


    private void markBagFull() {
        isFull = true;
    }

    public boolean isBagFull() {
        return isFull;
    }


    @Override
    public String toString() {
        return Thread.currentThread().getName() + " - Thief class - " + bag;
    }

}
