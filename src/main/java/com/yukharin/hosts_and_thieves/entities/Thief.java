package com.yukharin.hosts_and_thieves.entities;

import com.yukharin.hosts_and_thieves.comparators.ItemComparator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class Thief {

    private static final Comparator<Item> itemComparator = new ItemComparator().reversed();
    private Bag bag;
    private boolean isFull;

    public Thief() {
        this.bag = new Bag();
    }

    public void stealAll(Home home) {
        List<Item> allItems = getSortedItems(home);
        List<Item> itemsToSteal = bag.checkCapacity(allItems);
        bag.addAll(itemsToSteal);
        removeItems(itemsToSteal, home);
    }

    public void steal(Home home) {
        Item item = getMostValuable(home);
        boolean result = bag.checkCapacity(item);
        if (result) {
            bag.add(item);
            home.removeItem(item);
        } else {
            this.markBagFull();
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

    private void removeItems(List<Item> itemsToRemove, Home home) {
        for (Item item : itemsToRemove) {
            home.removeItem(item);
        }
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
