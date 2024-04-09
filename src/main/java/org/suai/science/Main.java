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
            graph = GraphGeneration.generateGraph(8);
            dfsCalculation.setGraph(graph);
            isGraphFullyConnected = dfsCalculation.isFullyConnectedGraph();
        }
        while (!isGraphFullyConnected);
        //System.out.println(graph);


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

        int[][] matrix = { // 3 tg (8 cycles: dfs - 3, alg - 18) isn't working
                {0,0,1,1,0,1,0,0},
                {0,0,1,1,0,1,0,1},
                {1,1,0,1,0,0,1,1},
                {1,1,1,0,1,0,0,0},
                {0,0,0,1,0,1,1,1},
                {1,1,0,0,1,0,0,0},
                {0,0,1,0,1,0,0,0},
                {0,1,1,0,1,0,0,0}
        };
        AdjacencyMatrixGraph graph8 = new AdjacencyMatrixGraph(matrix, matrix.length, 10);
        System.out.println(graph8);
        //System.out.println(graph);
        GraphCycleCounter cycleCounter = new GraphCycleCounter(graph8);
        System.out.println("Amount of 8-Cycles: " + cycleCounter.countAmountCyclesWithLength(8));

    }




}