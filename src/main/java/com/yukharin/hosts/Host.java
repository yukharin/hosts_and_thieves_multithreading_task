package com.yukharin.hosts;

import com.yukharin.homes.Home;
import com.yukharin.items.Item;
import com.yukharin.listeners.Listener;
import com.yukharin.utils.Utils;

import java.util.Collections;
import java.util.LinkedList;

public class Host {

    private LinkedList<Item> items;
    private Home home;


    public Host(Home home, int count) {
        this.home = home;
        LinkedList<Item> temp = Utils.generateItems(count);
        Listener.addHostList(Collections.unmodifiableList(temp));
        this.items = temp;
        System.out.println(this);
    }

    public void putElement() {
        Item item = items.poll();
        if (item != null) {
            home.add(item);
            Listener.removeHostItem(item);
            System.out.println(this);
        }
    }

    public int thingsCount() {
        return items.size();
    }

    @Override
    public String toString() {
        return Thread.currentThread().getName() + " - Host class - items: " + items;
    }


}
