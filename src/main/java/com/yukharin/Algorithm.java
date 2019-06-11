package com.yukharin;

import com.yukharin.items.Item;

import java.util.ArrayList;
import java.util.List;

public class Algorithm {


    private static final int WEIGHT = 4;

    public static void main(String[] args) {

//        int profit[] = {200, 240, 140, 150};
//        int weight[] = {1, 3, 2, 5};

        List<Item> items = new ArrayList<>();
        Item first = new Item(2, 2000);
        Item second = new Item(1, 5000);
        Item third = new Item(3, 500);
        Item forth = new Item(1, 1000);
        Item fifth = new Item(3, 3000);
        Item sixth = new Item(2, 4000);
        Item seventh = new Item(1, 3000);
        items.add(first);
        items.add(second);
        items.add(third);
        items.add(forth);
        items.add(fifth);
        items.add(sixth);
        items.add(seventh);
        solveKnapsackProblem(items, WEIGHT);
    }


    public static List<Item> solveKnapsackProblem(List<Item> allItems, int columns) {
        int rows = allItems.size();
        List<Item>[][] itemsTable = new List[rows + 1][columns + 1];
        initZeroItems(itemsTable, columns);
        List<Item> result = findOptimalList(allItems, itemsTable, rows, columns);
        removeZeroItems(result);
        System.out.println(result);
        return result;
    }

    private static List<Item> findOptimalList(List<Item> allItems, List<Item>[][] itemsTable, int rows, int columns) {
        for (int row = 0; row <= rows; row++) {
            for (int column = 0; column <= columns; column++) {
                if ((row == 0)) {
                    continue;
                }
                Item item = allItems.get(row - 1);
                int weight = item.getWeight();
                if ((weight <= column)) {
                    List<Item> bestCombination = itemsTable[row - 1][column];
                    List<Item> combinationToCompare = new ArrayList<>(itemsTable[row - 1][column - weight]);
                    combinationToCompare.add(item);
                    itemsTable[row][column] = max(bestCombination, combinationToCompare);
                } else {
                    itemsTable[row][column] = new ArrayList<>(itemsTable[row - 1][column]);
                }
            }
        }
        List<Item> result = itemsTable[rows][columns];
        return result;
    }

    private static void initZeroItems(List<Item>[][] itemsTable, int columns) {
        for (int row = 0; row < 1; row++) {
            for (int column = 0; column <= columns; column++) {
                List<Item> zeroItems = new ArrayList<>();
                zeroItems.add(new Item(0, 0));
                itemsTable[row][column] = zeroItems;
            }
        }
    }

    private static void removeZeroItems(List<Item> items) {
        Item item = new Item(0, 0);
        List<Item> itemsToRemove = new ArrayList<>();
        itemsToRemove.add(item);
        items.removeAll(itemsToRemove);
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
        return (firstValue > secondValue) ? firstList : secondList;
    }

}

