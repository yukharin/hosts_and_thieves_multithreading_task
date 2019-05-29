package com.yukharin.items;

public enum Item {

    TV(10, 25000), PLAYSTATION_4(5, 50000), PC(15, 40000), REFRIGERATOR(30, 25000),
    MICROWAVE_OVEN(6, 10000), LEGO(15, 13000);

    private int weight;
    private int value;

    Item(int weight, int value) {
        this.weight = weight;
        this.value = value;
    }

    public int getWeight() {
        return weight;
    }

    public int getValue() {
        return value;
    }


}
