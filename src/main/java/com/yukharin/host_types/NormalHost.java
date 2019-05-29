package com.yukharin.host_types;

import com.yukharin.home_types.Home;
import com.yukharin.items.Item;
import com.yukharin.utils.Utils;

import java.util.List;

public class NormalHost implements Host {
    private Home home;
    private List<Item> items;

    public NormalHost(Home home, int amountOfItems) {
        this.home = home;
        this.items = Utils.generateRandomListOfItems(amountOfItems);
    }


    public void put() {
        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);
            if (item != null) {
                home.put(item);
                items.set(i, null);
            }
        }
    }

    @Override
    public String toString() {
        return items.toString();
    }

}
