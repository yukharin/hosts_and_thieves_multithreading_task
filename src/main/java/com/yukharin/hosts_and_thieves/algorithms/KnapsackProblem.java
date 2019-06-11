package com.yukharin.hosts_and_thieves.algorithms;

import com.yukharin.hosts_and_thieves.entities.Item;

import java.util.ArrayList;
import java.util.List;

public class KnapsackProblem {

    private KnapsackProblem() {
    }

    public static List<Item> solveKnapsackProblem(List<Item> allItems, int columns) {
        int rows = allItems.size();
        List<Item> selectedItems;
        List<Item>[][] itemsTable = new List[rows + 1][columns + 1];
        initZeroItems(itemsTable, columns);
        selectedItems = findOptimalList(allItems, itemsTable, rows, columns);
        removeZeroItems(selectedItems);
        return selectedItems;
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
        List<Item> selectedItems = itemsTable[rows][columns];
        return selectedItems;
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
