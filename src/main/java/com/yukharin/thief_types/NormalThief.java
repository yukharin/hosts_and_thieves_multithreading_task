package com.yukharin.thief_types;

import com.yukharin.bags.Bag;
import com.yukharin.home_types.Home;
import com.yukharin.items.Item;

public class NormalThief implements Thief {


    private Home homeToSteal;
    private Bag bag;

    public NormalThief(Home home) {
        this.bag = new Bag();
        this.homeToSteal = home;
    }

    @Override
    public void steal() {
        while (true) {
            boolean flag = bag.add((Item) homeToSteal.get());
            if (flag == false) {
                break;
            }
        }
    }


    @Override
    public String toString() {
        return bag.toString();
    }

}
