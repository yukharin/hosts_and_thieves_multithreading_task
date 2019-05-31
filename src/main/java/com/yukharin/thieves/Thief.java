package com.yukharin.thieves;

import com.yukharin.bags.Bag;
import com.yukharin.homes.Home;
import com.yukharin.items.Item;

import java.util.Iterator;

public class Thief {

    private Home home;
    private Bag bag;


    public Thief(Home home) {
        this.home = home;
        this.bag = new Bag();
    }

    public void steal() {
        home.sortItems();
        Iterator<Item> iterator = home.iterator();
        while (iterator.hasNext()) {
            Item item = iterator.next();
            boolean flag = bag.add(item);
            if (flag == false) {
                break;
            } else {

                home.removeItem(item);
            }
            System.out.println(Thread.currentThread().getName() + " - Thief - stealing from home " + bag);
        }
        home.shuffleItems();
    }

    public Home getHome() {
        return home;
    }

    @Override
    public String toString() {
        return Thread.currentThread().getName() + " - Thief class - " + bag;
    }

}
