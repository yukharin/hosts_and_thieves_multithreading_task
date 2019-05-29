package com.yukharin.thief_types;

import com.yukharin.bags.Bag;
import com.yukharin.home_types.Home;

public interface Thief {

    void randomSteal();

    void smartSteal();

    Home getHomeToSteal();

    Bag getBag();

}
