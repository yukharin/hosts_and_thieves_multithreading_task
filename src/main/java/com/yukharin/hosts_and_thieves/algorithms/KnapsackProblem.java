package com.yukharin.hosts_and_thieves.algorithms;

import com.yukharin.hosts_and_thieves.entities.Item;

import java.util.ArrayList;
import java.util.List;

public class KnapsackProblem {

    private KnapsackProblem() {
    }

    public static List<Item> solveKnapsackProblem(final List<Item> allItems, final int columns) {
        final int rows = allItems.size();
        final List<Item> selectedItems;
        final List<Item>[][] itemsTable = new List[rows + 1][columns + 1];
        initZeroItems(itemsTable, columns);
        selectedItems = findOptimalList(allItems, itemsTable, rows, columns);
        removeZeroItems(selectedItems);
        return selectedItems;
    }

    private static List<Item> findOptimalList(final List<Item> allItems, final List<Item>[][] itemsTable, final int rows, final int columns) {
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
        return itemsTable[rows][columns];
    }

    private static void initZeroItems(final List<Item>[][] itemsTable, final int columns) {
        for (int row = 0; row < 1; row++) {
            for (int column = 0; column <= columns; column++) {
                List<Item> zeroItems = new ArrayList<>();
                zeroItems.add(new Item(0, 0));
                itemsTable[row][column] = zeroItems;
            }
        }
    }

    private static void removeZeroItems(final List<Item> items) {
        Item item = new Item(0, 0);
        List<Item> itemsToRemove = new ArrayList<>();
        itemsToRemove.add(item);
        items.removeAll(itemsToRemove);
    }


    private static List<Item> max(final List<Item> firstList, final List<Item> secondList) {
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
