package com.yukharin;

import com.yukharin.items.Item;

import java.util.ArrayList;
import java.util.List;

public class Algorithm {


    private static final int WEIGHT = 5;

    public static void main(String[] args) {

        List<Item> items = new ArrayList<>();
        Item first = new Item(1, 20);
        Item second = new Item(2, 10);
        Item third = new Item(3, 15);
        Item forth = new Item(4, 50);
        Item fifth = new Item(5, 70);
        Item sixth = new Item(3, 25);
        items.add(first);
        items.add(second);
        items.add(third);
        items.add(forth);
        items.add(fifth);
        items.add(sixth);

        List<Item>[][] itemsTable = new ArrayList[items.size()][WEIGHT];

        int columns = WEIGHT;
        int rows = itemsTable.length;
        System.out.println(rows);
        for (int i = 1; i <= rows; i++) {
            Item item;
            List<Item> bestCombination;
            List<Item> combinationToCompare;
            for (int j = 1; j <= columns; j++) {
                item = items.get(i);
                int weight = item.getWeight();
                System.out.println("Item: " + item);
                if (i == 0) {
                    bestCombination = new ArrayList<>();
                    bestCombination.add(item);
                    itemsTable[i][j] = bestCombination;
                } else {
                    if ((j - weight) < 0) {
                        bestCombination = new ArrayList<>(itemsTable[i - 1][j]);
                        combinationToCompare = new ArrayList<>();
                        combinationToCompare.add(item);
                        itemsTable[i][j] = max(bestCombination, combinationToCompare);
                    } else {
                        bestCombination = new ArrayList<>(itemsTable[i - 1][j]);
                        System.out.println("best " + bestCombination);
                        combinationToCompare = new ArrayList<>(itemsTable[i - 1][j - weight]);
                        System.out.println("compare before: " + combinationToCompare);
                        combinationToCompare.add(item);
                        System.out.println("compare after: " + combinationToCompare);
                        itemsTable[i][j] = max(bestCombination, combinationToCompare);
                        System.out.println("third : " + itemsTable[i][j]);
                    }
                }
            }
            List<Item> best = itemsTable[rows][columns];
            System.out.println(best);
        }
    }

    private static List<Item> max(List<Item> firstList, List<Item> secondList) {
        int firstValue = 0;
        int secondValue = 0;
        for (Item item : firstList) {
            firstValue += item.getValue();
        }
        for (Item item : secondList) {
            secondValue += item.getValue();
        }

        if (firstValue > secondValue) {
            return firstList;
        } else return secondList;
    }

    private static int getCombinationWeight(List<Item> items) {
        int sumWeight = 0;
        for (Item item : items) {
            sumWeight += item.getWeight();
        }
        return sumWeight;
    }

    private static int getCombinationValue(List<Item> items) {
        int sumValue = 0;
        for (Item item : items) {
            sumValue += item.getWeight();
        }
        return sumValue;
    }
}

