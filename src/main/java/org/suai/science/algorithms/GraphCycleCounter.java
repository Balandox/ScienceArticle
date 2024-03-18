package org.suai.science.algorithms;

import org.suai.science.model.AdjacencyMatrixGraph;
import org.suai.science.utils.CycleCounterUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class GraphCycleCounter {

    private AdjacencyMatrixGraph graph;

    public GraphCycleCounter(AdjacencyMatrixGraph graph) {
        this.graph = graph;
    }

    public GraphCycleCounter(){}

    public Long countAmountCyclesWithLength(int length){
        switch (length){
            case 4:
                return countFourLengthCycles();
            default:
                return -1L;
        }
    }

    private Long countFourLengthCycles(){
        int[][] matrix = this.graph.getMatrix();
        int amountOfVertex = this.graph.getAmountOfVertex();
        long w = CycleCounterUtil.calculatePossibleRowsCombination(2, amountOfVertex);
        List<List<Integer>> combinations = CycleCounterUtil.generateCombinations(2, amountOfVertex);
        //System.out.println(combinations);
        List<Long> Pi = new ArrayList<>();

        for(List<Integer> combination : combinations){
            int[] firstRow = matrix[combination.get(0)];
            int[] secondRow = matrix[combination.get(1)];
            for(int i = 0; i < secondRow.length; i++)
                if(secondRow[i] == 1)
                    secondRow[i] = 2;
            int[] sumRow = this.getSumRow(firstRow, secondRow);
            int num3 = getAmountOfSpecifiedElemInRow(3, sumRow);
            long pi = CycleCounterUtil.nCr(num3, 2);
            Pi.add(pi);
        }

        return CycleCounterUtil.calculateTotalNumberOfCycles(Pi);
    }

    private int[] getSumRow(int[]... rows){
        int[] sumRow = new int[rows[0].length];
        for(int[] row : rows)
            for(int i = 0; i < row.length; i++)
                sumRow[i] += row[i];
        return sumRow;
    }

    private int getAmountOfSpecifiedElemInRow(int elem, int[] sumRow){
        int count = 0;
        for(int i = 0; i < sumRow.length; i++)
            if(sumRow[i] == elem)
                count++;
        return count;
    }
}
