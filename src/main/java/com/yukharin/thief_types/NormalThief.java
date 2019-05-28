package com.yukharin.thief_types;

import com.yukharin.bags.Bag;
import com.yukharin.home_types.Home;
import com.yukharin.items.Item;

public class NormalThief extends AbstractThief implements Thief {

    public NormalThief(Home home) {
        this.bag = new Bag();
        this.homeToSteal = home;
    }

    public void steal() {
        while (true) {
            Object object = homeToSteal.get();
            if (object instanceof Item) {
                boolean flag = bag.add((Item) object);
                if (flag == false) {
                    break;
                }
            }
        }
    }

    @Override
    public String toString() {
        return bag.toString();
    }

}
