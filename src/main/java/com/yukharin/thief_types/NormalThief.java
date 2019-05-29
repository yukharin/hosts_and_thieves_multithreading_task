package com.yukharin.thief_types;

import com.yukharin.bags.Bag;
import com.yukharin.home_types.Home;
import com.yukharin.items.Item;

import java.util.List;

public class NormalThief implements Thief {


    private Home homeToSteal;
    private Bag bag;

    public NormalThief(Home home) {
        this.bag = new Bag();
        this.homeToSteal = home;
    }

    @Override
    public void randomSteal() {
        while (true) {
            Object object = homeToSteal.getRandomElement();
            if (object instanceof Item) {
                boolean flag = bag.add((Item) object);
                if (flag == false) {
                    break;
                }
            }
        }
    }

    @Override
    public void smartSteal() {
        homeToSteal.sortElements();
        List<Item> items = homeToSteal.getAllElements();
        for (int i = 0; i < items.size(); i++) {
            boolean flag = bag.add(items.get(i));
            if (flag == false) {
                break;
            } else items.remove(items.get(i));
        }
    }

    @Override
    public Home getHomeToSteal() {
        return homeToSteal;
    }

    public Bag getBag() {
        return bag;
    }

    @Override
    public String toString() {
        return bag.toString();
    }

}
