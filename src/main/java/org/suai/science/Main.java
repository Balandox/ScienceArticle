package org.suai.science;

import org.suai.science.algorithms.DfsCalculation;
import org.suai.science.algorithms.GraphCycleCounter;
import org.suai.science.algorithms.GraphCycleFinder;
import org.suai.science.generation.GraphGeneration;
import org.suai.science.model.AdjacencyMatrixGraph;
import org.suai.science.utils.BipartiteCheckingUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        AdjacencyMatrixGraph graph = null;
        Boolean isGraphFullyConnected = false;
        Boolean isBipartiteGraph = false;
        int count = 0;
        DfsCalculation dfsCalculation = new DfsCalculation();
        do {
            graph = GraphGeneration.generateGraph(14);
            dfsCalculation.setGraph(graph);
            isGraphFullyConnected = dfsCalculation.isFullyConnectedGraph();
            isBipartiteGraph = BipartiteCheckingUtil.isBipartite(graph);
        }
        while (!isGraphFullyConnected || !isBipartiteGraph);
        System.out.println(isGraphFullyConnected + " " + isBipartiteGraph);


/*               int[][] matrix = {   //1; result 1
                {0,1,1,1,1,0},
                {1,0,0,0,1,0},
                {1,0,0,0,0,0},
                {1,0,0,0,0,1},
                {1,1,0,0,0,1},
                {0,0,0,1,1,0}
        };*/


/*                int[][] matrix = {   //2; result 2
                {0,0,1,0,1,1},
                {0,0,0,1,0,0},
                {1,0,0,1,1,1},
                {0,1,1,0,1,0},
                {1,0,1,1,0,0},
                {1,0,1,0,0,0}
        };*/
/*
               int[][] matrix = {   //3 result 0
                {0,1,1,0,1},
                {1,0,0,0,1},
                {1,0,0,1,0},
                {0,0,1,0,0},
                {0,0,1,0,0}
        }; */

/*                int[][] matrix = {            //8; result 2
                {0,0,0,1,1,0},
                {0,0,0,1,1,1},
                {0,0,0,0,1,1},
                {1,1,0,0,0,0},
                {1,1,1,0,0,0},
                {0,1,1,0,0,0}
        };*/
/*                int[][] matrix = {   //10 result >= 3
                {0,1,1,0,0,1},
                {1,0,0,1,1,0},
                {1,0,0,1,0,1},
                {0,1,1,0,0,0},
                {0,1,0,0,0,1},
                {1,0,1,0,1,0}
        };*/
/*        int[][] matrix = { // 1 tg (6 cycles: dfs - 0, alg - 11) isn't working
                {0,0,0,0,1,0},
                {0,0,0,1,0,1},
                {0,0,0,1,1,0},
                {0,1,1,0,1,1},
                {1,0,1,1,0,1},
                {0,1,0,1,1,0}
        };*/

/*
        int[][] matrix = { // 2 tg (6 cycles: dfs - 0, alg - 4) isn't working
                {0,0,1,1,0,0},
                {0,0,0,1,1,1},
                {1,0,0,0,0,0},
                {1,1,0,0,0,1},
                {0,1,0,0,0,1},
                {0,1,0,1,1,0}
        };
*/

        /*int[][] matrix = { // 3 tg (8 cycles: dfs - 3, alg - 18) isn't working
                {0,0,1,1,0,1,0,0},
                {0,0,1,1,0,1,0,1},
                {1,1,0,1,0,0,1,1},
                {1,1,1,0,1,0,0,0},
                {0,0,0,1,0,1,1,1},
                {1,1,0,0,1,0,0,0},
                {0,0,1,0,1,0,0,0},
                {0,1,1,0,1,0,0,0}
        };*/
        //AdjacencyMatrixGraph graph8 = new AdjacencyMatrixGraph(matrix, matrix.length, 10);
        System.out.println(graph);
        int[][] edgesListGraph = GraphGeneration.convertAdjacencyMatrixToEdgesList(graph);
        GraphCycleFinder.launchCycleFinder(edgesListGraph);
        GraphCycleCounter cycleCounter4 = new GraphCycleCounter(new AdjacencyMatrixGraph(graph));
        GraphCycleCounter cycleCounter6 = new GraphCycleCounter(graph);
        //GraphCycleCounter cycleCounter8 = new GraphCycleCounter(new AdjacencyMatrixGraph(graph));
        System.out.println("Amount of 4-Cycles: " + cycleCounter4.countAmountCyclesWithLength(4));
        System.out.println("Amount of 6-Cycles: " + cycleCounter6.countAmountCyclesWithLength(6));
        //System.out.println("Amount of 8-Cycles: " + cycleCounter8.countAmountCyclesWithLength(8));


    }




}