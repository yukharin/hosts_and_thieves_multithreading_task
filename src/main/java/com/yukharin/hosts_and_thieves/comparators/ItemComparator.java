package com.yukharin.hosts_and_thieves.comparators;

import com.yukharin.hosts_and_thieves.entities.Item;

import java.util.Comparator;

public class ItemComparator implements Comparator<Item> {


    @Override
    public int compare(Item o1, Item o2) {
        return Integer.compare(o1.getValue(), o2.getValue());
    }

}
