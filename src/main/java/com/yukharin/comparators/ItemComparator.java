package com.yukharin.comparators;

import com.yukharin.items.Item;

import java.util.Comparator;

public class ItemComparator implements Comparator<Item> {

    @Override
    public int compare(Item first, Item second) {
        if (first.getValue() == second.getValue()) {
            return 0;
        } else if (first.getValue() > second.getValue()) {
            return -1;
        } else
            return 1;
    }
}
