package org.suai.science;

import org.suai.science.algorithms.DfsCalculation;
import org.suai.science.algorithms.GraphCycleCounter;
import org.suai.science.generation.GraphGeneration;
import org.suai.science.model.AdjacencyMatrixGraph;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        AdjacencyMatrixGraph graph = null;
        Boolean isGraphFullyConnected = false;
        DfsCalculation dfsCalculation = new DfsCalculation();
        do {
            graph = GraphGeneration.generateGraph(6);
            dfsCalculation.setGraph(graph);
            isGraphFullyConnected = dfsCalculation.isFullyConnectedGraph();
        }
        while (!isGraphFullyConnected);
        //System.out.println(graph);

/*        int[][] matrix = {
                {0,0,0,1,1,0},
                {0,0,0,1,1,1},
                {0,0,0,0,1,1},
                {1,1,0,0,0,0},
                {1,1,1,0,0,0},
                {0,1,1,0,0,0}
        };
                int[][] matrix = {
                {0,1,1,0,0,1},
                {1,0,0,1,1,0},
                {1,0,0,1,0,1},
                {0,1,1,0,0,0},
                {0,1,0,0,0,1},
                {1,0,1,0,1,0}
        };

        AdjacencyMatrixGraph graph4 = new AdjacencyMatrixGraph(matrix, matrix.length, 0);*/
        //AdjacencyMatrixGraph graph6 = new AdjacencyMatrixGraph(graph);
        System.out.println(graph);
        GraphCycleCounter cycleCounter4 = new GraphCycleCounter(graph);
        //GraphCycleCounter cycleCounter6 = new GraphCycleCounter(graph6);
        System.out.println("Amount of 4-Cycles: " + cycleCounter4.countAmountCyclesWithLength(4));
        //System.out.println("Amount of 6-Cycles: " + cycleCounter6.countAmountCyclesWithLength(6));
    }


}