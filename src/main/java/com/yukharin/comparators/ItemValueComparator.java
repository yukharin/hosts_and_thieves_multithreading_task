package com.yukharin.comparators;

import com.yukharin.items.Item;

import java.util.Comparator;

public class ItemValueComparator implements Comparator<Item> {


    @Override
    public int compare(Item o1, Item o2) {
        return Integer.compare(o1.getValue(), o2.getValue());
    }

}
