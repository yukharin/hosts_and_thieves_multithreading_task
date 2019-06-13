package com.yukharin.hosts_and_thieves.entities;

import com.yukharin.hosts_and_thieves.algorithms.KnapsackProblem;
import com.yukharin.hosts_and_thieves.comparators.ItemValueComparator;

import java.util.*;

public final class Thief {

    private static final Comparator<Item> itemComparator = new ItemValueComparator().reversed();
    private final Bag bag;
    private boolean isFull;

    public Thief(final int weight_limit) {
        this.bag = new Bag(weight_limit);
    }

    public void stealAll(final Home home) {
        Objects.requireNonNull(home);
        List<Item> allItems = getAllItems(home);
        List<Item> selectedItems = KnapsackProblem.solveKnapsackProblem(allItems, bag.getMaxWeight());
        putAndRemoveItems(home, selectedItems);
    }

    public void steal(final Home home) {
        Objects.requireNonNull(home);
        List<Item> allItems = getAllItems(home);
        Item item = getMostValuable(allItems);
        putAndRemoveItem(home, item);
    }

    private List<Item> getAllItems(final Home home) {
        ArrayList<Item> allItems = new ArrayList<>(home.countItems());
        Iterator<Item> iterator = home.iterator();
        while (iterator.hasNext()) {
            allItems.add(iterator.next());
        }
        return allItems;
    }

    private void sortItems(final List<Item> items) {
        items.sort(itemComparator);
    }

    private Item getMostValuable(final List<Item> allItems) {
        Item maxItem = new Item(0, 0);
        for (Item item : allItems) {
            if ((maxItem.compareTo(item)) < 0) {
                maxItem = item;
            }
        }
        return maxItem;
    }

    private void putAndRemoveItems(final Home home, final List<Item> allItems) {
        for (Item item : allItems) {
            if (bag.isEnoughSpace(item)) {
                bag.add(item);
                home.removeItem(item);
            } else {
                markBagFull();
                break;
            }
        }
    }

    private void putAndRemoveItem(final Home home, final Item item) {
        if (bag.isEnoughSpace(item)) {
            bag.add(item);
            home.removeItem(item);
        } else {
            markBagFull();
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