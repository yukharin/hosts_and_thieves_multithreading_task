package com.yukharin;

public enum Item {

    TV(2, 2000), LAPTOP(1, 5000), CHAIRE(3, 500), TABLE_LAMP(1, 1000),
    MIRROR(3, 3000), PAINTING(2, 4000), CLOCK(1, 3000);

    private int weight;
    private int value;

    Item(int weight, int value) {
        this.weight = weight;
        this.value = value;
    }

//    TV(Weight: 2 Kg, Market Value: 2000)
//    Laptop(Weight: 1 Kg, Market Value: 5000)
//    Chairs(Weight: 3 Kg, Market Value: 500)
//    Table Lamp(Weight: 1 Kg, Market Value: 1000)
//    Mirror(Weight: 3 Kg, Market Value: 3000)
//    Painting(Weight: 2 Kg, Market Value: 4000)
//    Clock(Weight: 1 Kg, Market Value: 3000) etc.


    public int getWeight() {
        return weight;
    }

    public int getValue() {
        return value;
    }


}
