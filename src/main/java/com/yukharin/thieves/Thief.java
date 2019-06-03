package com.yukharin.thieves;

import com.yukharin.bags.Bag;
import com.yukharin.comparators.ItemComparator;
import com.yukharin.homes.Home;
import com.yukharin.items.Item;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class Thief {

    private Bag bag;

    public Thief() {
        this.bag = new Bag();
    }

    public void steal(Home home) {
        Comparator<Item> itemComparator = new ItemComparator();
        List<Item> itemsToSteal = new ArrayList<>();
        Iterator<Item> iterator = home.iterator();
        while (iterator.hasNext()) {
            itemsToSteal.add(iterator.next());
        }
        itemsToSteal.sort(itemComparator.reversed());
        putIntoBag(home, itemsToSteal);
    }

    private void putIntoBag(Home home, List<Item> items) {
        for (Item item : items) {
            boolean flag = bag.add(item);
            if (flag == false) {
                break;
            } else {
                home.removeItem(item);
            }
        }
    }


    @Override
    public String toString() {
        return Thread.currentThread().getName() + " - Thief class - " + bag;
    }

}
