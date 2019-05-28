package com.yukharin.host_types;

import com.yukharin.home_types.Home;
import com.yukharin.items.Item;
import com.yukharin.utils.Utils;

public class NormalHost extends AbstractHost implements Host {


    public NormalHost(Home home, int size) {
        this.home = home;
        this.items = Utils.generateRandomListOfItems(size);
    }


    public void put() {
        for (Item item : items) {
            this.home.put(item);
            System.out.println(home);
        }
    }

    @Override
    public String toString() {
        return items.toString();
    }

}
