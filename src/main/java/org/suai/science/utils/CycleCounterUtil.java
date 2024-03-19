package org.suai.science.utils;

import java.util.ArrayList;
import java.util.List;

public class CycleCounterUtil {

    public static long calculatePossibleRowsCombination(int amountOfRows, int totalRows){
        return nCr(totalRows, amountOfRows);
    }

    public static long calculateTotalNumberOfCycles(List<Long> Pi){
        return  Pi.stream().mapToLong(Long::longValue).sum();
    }

    public static List<List<Integer>> generateCombinations(int rowsToSelect, int totalRows) {
        List<List<Integer>> combinations = new ArrayList<>();
        List<Integer> currentCombination = new ArrayList<>();
        generateCombinationsHelper(totalRows, rowsToSelect, 0, currentCombination, combinations);
        return combinations;
    }

    private static void generateCombinationsHelper(int totalRows, int rowsToSelect, int startRow,
                                                   List<Integer> currentCombination, List<List<Integer>> combinations) {
        if (rowsToSelect == 0) {
            combinations.add(new ArrayList<>(currentCombination));
            return;
        }

        for (int i = startRow; i < totalRows; i++) {
            currentCombination.add(i);
            generateCombinationsHelper(totalRows, rowsToSelect - 1, i + 1, currentCombination, combinations);
            currentCombination.remove(currentCombination.size() - 1);
        }
    }

    public static long nCr(int n, int r) {
        return fact(n) / (fact(r) * fact(n - r));
    }

    private static long fact(int n) {
        if(n==0)
            return 1;
        long res = 1;
        for (int i = 2; i <= n; i++)
            res = res * i;
        return res;
    }

}
