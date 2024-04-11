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
            case 6:
                return countSixLengthCycles();
            case 8:
                return countEightLengthCycles();
            default:
                return -1L;
        }
    }

    private Long countEightLengthCycles(){
        int[][] matrix = this.graph.getMatrix();
        int amountOfVertex = this.graph.getAmountOfVertex();
        List<List<Integer>> combinations = CycleCounterUtil.generateCombinations(4, amountOfVertex);
        //System.out.println(combinations);
        List<Long> Pi = new ArrayList<>();

        for(List<Integer> combination : combinations){
            int[] firstRow = Arrays.copyOf(matrix[combination.get(0)], matrix[0].length);
            int[] secondRow = Arrays.copyOf(matrix[combination.get(1)], matrix[0].length);
            int[] thirdRow = Arrays.copyOf(matrix[combination.get(2)], matrix[0].length);
            int[] fourthRow = Arrays.copyOf(matrix[combination.get(3)], matrix[0].length);
            this.replaceRowsElementsBeforeSum(8, secondRow, thirdRow, fourthRow);
            //System.out.println("fir" + Arrays.toString(firstRow));
            //System.out.println("sec" + Arrays.toString(secondRow));
            //System.out.println("thi" + Arrays.toString(thirdRow));
            //System.out.println("fou" + Arrays.toString(fourthRow));

            int[] sumRow = this.getSumRow(firstRow, secondRow, thirdRow, fourthRow);
            //System.out.println("sumRow" + Arrays.toString(sumRow));
            int num3 = getAmountOfSpecifiedElemInRow(3, sumRow);
            int num5 = getAmountOfSpecifiedElemInRow(5, sumRow);
            int num6 = getAmountOfSpecifiedElemInRow(6, sumRow);
            int num9 = getAmountOfSpecifiedElemInRow(9, sumRow);
            int num10 = getAmountOfSpecifiedElemInRow(10, sumRow);
            int num12 = getAmountOfSpecifiedElemInRow(12, sumRow);
            long pi = (((long) num3*num12*num5*num10) + ((long) num3*num6*num9*num12) + ((long) num5*num6*num9*num10));
            //System.out.println("pi = " + pi + " on combination " + combination);
            //System.out.println();
            Pi.add(pi);
        }

        return CycleCounterUtil.calculateTotalNumberOfCycles(Pi);
    }

    private Long countSixLengthCycles(){
        int[][] matrix = this.graph.getMatrix();
        int amountOfVertex = this.graph.getAmountOfVertex();
        List<List<Integer>> combinations = CycleCounterUtil.generateCombinations(3, amountOfVertex);
        //System.out.println(combinations);
        List<Long> Pi = new ArrayList<>();

        for(List<Integer> combination : combinations){
            int[] firstRow = Arrays.copyOf(matrix[combination.get(0)], matrix[0].length);
            int[] secondRow = Arrays.copyOf(matrix[combination.get(1)], matrix[0].length);
            int[] thirdRow = Arrays.copyOf(matrix[combination.get(2)], matrix[0].length);
            this.replaceRowsElementsBeforeSum(6, secondRow, thirdRow);
            //System.out.println("fir" + Arrays.toString(firstRow));
            //System.out.println("sec" + Arrays.toString(secondRow));
            //System.out.println("thi" + Arrays.toString(thirdRow));

            int[] sumRow = this.getSumRow(firstRow, secondRow, thirdRow);
            //System.out.println("sumRow" + Arrays.toString(sumRow));
            int num3 = getAmountOfSpecifiedElemInRow(3, sumRow);
            int num5 = getAmountOfSpecifiedElemInRow(5, sumRow);
            int num6 = getAmountOfSpecifiedElemInRow(6, sumRow);
            int num7 = getAmountOfSpecifiedElemInRow(7, sumRow);
            long pi = (((long) num3*num6*num5) + ((long) num7*num6*num5) + ((long) num3*num7*num5) + ((long) num3*num6*num7));
            //System.out.println("pi = " + pi + " on combination " + combination);
            //System.out.println();
            Pi.add(pi);
        }

        return CycleCounterUtil.calculateTotalNumberOfCycles(Pi);
    }


    private Long countFourLengthCycles(){
        int[][] matrix = this.graph.getMatrix();
        int amountOfVertex = this.graph.getAmountOfVertex();
        List<List<Integer>> combinations = CycleCounterUtil.generateCombinations(2, amountOfVertex);
        List<Long> Pi = new ArrayList<>();

        for(List<Integer> combination : combinations){
            int[] firstRow = Arrays.copyOf(matrix[combination.get(0)], matrix[0].length);
            int[] secondRow = Arrays.copyOf(matrix[combination.get(1)], matrix[0].length);
            this.replaceRowsElementsBeforeSum(4, secondRow);
            int[] sumRow = this.getSumRow(firstRow, secondRow);
            int num3 = getAmountOfSpecifiedElemInRow(3, sumRow);
            long pi = CycleCounterUtil.nCr(num3, 2);
            Pi.add(pi);
        }

        return CycleCounterUtil.calculateTotalNumberOfCycles(Pi);
    }

    private void replaceRowsElementsBeforeSum(int cycleLength, int[]... rows){
        switch (cycleLength){
            case 4: // contain only one row for changing
                this.changeRowElementsOnSpecified(2, rows[0]); // second row 1->2
                break;
            case 6:
                this.changeRowElementsOnSpecified(2, rows[0]); // second row 1->2
                this.changeRowElementsOnSpecified(4, rows[1]); // third row 1->4
                break;
            case 8:
                this.changeRowElementsOnSpecified(2, rows[0]); // second row 1->2
                this.changeRowElementsOnSpecified(4, rows[1]); // third row 1->4
                this.changeRowElementsOnSpecified(8, rows[2]); // fourth row 1->8
                break;

        }
    }

    private int[] getSumRow(int[]... rows){
        int[] sumRow = new int[rows[0].length];
        for(int[] row : rows)
            for(int i = 0; i < row.length; i++)
                sumRow[i] += row[i];
        return sumRow;
    }

    private void changeRowElementsOnSpecified(int elemToReplaceOn, int[] row){
        for(int i = 0; i < row.length; i++)
            if(row[i] == 1)
                row[i] = elemToReplaceOn;
    }

    private int getAmountOfSpecifiedElemInRow(int elem, int[] sumRow){
        int count = 0;
        for(int i = 0; i < sumRow.length; i++)
            if(sumRow[i] == elem)
                count++;
        return count;
    }
}
