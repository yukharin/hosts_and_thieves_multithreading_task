package com.yukharin;

import java.util.Arrays;

public class KnapsackProblem {


    public static void main(String[] args) {
        int knapsackMaxWeight = 5;
        int[] profit = {200, 240, 140, 150};
        int[] weight = {1, 3, 2, 5};

        int maxProfit = maximizeProfit(weight, profit, knapsackMaxWeight);
        System.out.println(maxProfit);

        int[] ints = new int[10];
        System.out.println(Arrays.toString(ints));
    }


    private static int maximizeProfit(int[] weight, int[] profit, int max) {
        int[][] temp = new int[weight.length + 1][max + 1];

        for (int row = 0; row <= weight.length; row++) {
            for (int col = 0; col <= max; col++) {

//Represent 0 item OR Sack with capacity 0
                if (row == 0 || col == 0) {
                    continue;
                }

//If col represent Sack weight and if it is >= item weight,
//it means item is eligible to be picked.
                if (col >= weight[row - 1]) {

//Checking picking the item will give Max profit or not picking the item will give Max profit.
                    temp[row][col] = Math.max(profit[row - 1] + temp[row - 1][col - weight[row - 1]], temp[row - 1][col]);
                } else {

//Sack weight is less than item weight, So can't pick item and Max profit we
//can get at this point is profit we got in previous step by not picking current item
                    temp[row][col] = temp[row - 1][col];
                }
            }
        }
        return temp[weight.length][max];
    }
}