package com.yukharin.thieves;

import com.yukharin.bags.Bag;
import com.yukharin.homes.Home;
import com.yukharin.items.Item;
import com.yukharin.listeners.Listener;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Thief {

    private Home home;
    private Bag bag;

    public Thief(Home home) {
        this.home = home;
        this.bag = new Bag();
    }

    public void steal() {
        List<Item> items = home.getItems();
        Collections.sort(items);
        Iterator<Item> iterator = items.iterator();
        while (iterator.hasNext()) {
            Item item = iterator.next();
            boolean flag = bag.add(item);
            if (flag == false) {
                break;
            } else {
                items.remove(item);
                Listener.addThiefItem(item);
            }
            System.out.println(this);
        }
        Collections.shuffle(items);
    }

    public Home getHome() {
        return home;
    }

    @Override
    public String toString() {
        return Thread.currentThread().getName() + " - Thief class - " + bag;
    }

}
