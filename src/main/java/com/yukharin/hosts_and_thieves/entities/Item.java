package com.yukharin.hosts_and_thieves.entities;

public final class Item implements Comparable<Item> {

    private int weight;
    private int value;

    public Item(int weight, int value) {
        this.weight = weight;
        this.value = value;
    }

    public int getWeight() {
        return weight;
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item)) return false;
        Item item = (Item) o;
        if (weight != item.weight) return false;
        return value == item.value;
    }

    @Override
    public int hashCode() {
        int result = weight;
        result = 31 * result + value;
        return result;
    }

    @Override
    public String toString() {
        return "Item{" +
                "weight=" + weight +
                ", value=" + value +
                '}';
    }

    @Override
    public int compareTo(Item item) {
        return Integer.compare(value, item.getValue());
    }
}