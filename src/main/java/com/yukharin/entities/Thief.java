package com.yukharin.entities;

import com.yukharin.comparators.ItemComparator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Thief {

    private Bag bag;

    public Thief() {
        this.bag = new Bag();
    }

    public void steal(Home home) {
        List<Item> itemsToSteal = getSortedItems(home);
        List<Item> itemsToRemove = bag.addAll(itemsToSteal);
        removeItems(itemsToRemove, home);
    }

    private List<Item> getSortedItems(Home home) {
        ArrayList<Item> sortedItems = new ArrayList<>(home.countItems());
        Iterator<Item> iterator = home.iterator();
        while (iterator.hasNext()) {
            sortedItems.add(iterator.next());
        }
        sortedItems.sort(new ItemComparator().reversed());
        return sortedItems;
    }

    private void removeItems(List<Item> itemsToRemove, Home home) {
        for (Item item : itemsToRemove) {
            home.removeItem(item);
        }
    }


    @Override
    public String toString() {
        return Thread.currentThread().getName() + " - Thief class - " + bag;
    }

}
