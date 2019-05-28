package com.yukharin.host_types;

import com.yukharin.home_types.Home;
import com.yukharin.items.Item;

import java.util.List;

public abstract class AbstractHost implements Host {

    protected Home home;
    protected List<Item> items;

}
