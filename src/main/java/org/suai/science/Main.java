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
        DfsCalculation dfsCalculation = new DfsCalculation();
        do {
            graph = GraphGeneration.generateGraph(6);
            dfsCalculation.setGraph(graph);
            isGraphFullyConnected = dfsCalculation.isFullyConnectedGraph();
            isBipartiteGraph = BipartiteCheckingUtil.isBipartite(graph);
        }
        while (!isGraphFullyConnected || !isBipartiteGraph);


/*               int[][] matrix = {   //слайд 6 пример
                {0, 1, 0, 1, 0},
                {1, 0, 1, 0, 0,},
                {0, 1, 0, 1, 0,},
                {1, 0, 1, 0, 1, },
                {0, 0, 0, 1, 0, }
        };*/


                int[][] matrix = {   // слайд 7 пример
                {0, 0, 1, 1, 0, 1},
                {0, 0, 0, 1, 0, 1},
                {1, 0, 0, 0, 1, 0},
                {1, 1, 0, 0, 0, 0},
                {0, 0, 1, 0, 0, 1},
                {1, 1, 0, 0, 1, 0}
        };
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
        /*        int[][] matrix = {   //10 result >= 3
                {0,1,1,0,0,1},
                {1,0,0,1,1,0},
                {1,0,0,1,0,1},
                {0,1,1,0,0,0},
                {0,1,0,0,0,1},
                {1,0,1,0,1,0}
        };*/
/*        int[][] matrix = { // example 3 in table 12 vertex
                {0,1,0,1,0,0,0,0,0,0,0,0,},
                {1,0,0,0,1,0,0,0,0,0,1,0,},
                {0,0,0,1,0,0,0,0,0,0,0,0},
                {1,0,1,0,0,0,0,1,0,0,0,0},
                {0,1,0,0,0,1,0,0,1,1,0,0},
                {0,0,0,0,1,0,0,1,0,0,0,0},
                {0,0,0,0,0,0,0,1,0,0,1,1},
                {0,0,0,1,0,1,1,0,0,0,0,0},
                {0,0,0,0,1,0,0,0,0,0,1,1},
                {0,0,0,0,1,0,0,0,0,0,0,0},
                {0,1,0,0,0,0,1,0,1,0,0,0},
                {0,0,0,0,0,0,1,0,1,0,0,0}
        };*/


/*        int[][] matrix = { // example 1 in table 8 vertex
                {0,0,1,0,0,0,0,1},
                {0,0,0,1,1,0,0,1},
                {1,0,0,0,0,0,1,0},
                {0,1,0,0,0,0,0,0},
                {0,1,0,0,0,0,1,0},
                {0,0,0,0,0,0,1,0},
                {0,0,1,0,1,1,0,0},
                {1,1,0,0,0,0,0,0}
        };*/


/*        int[][] matrix = { // example 2 in table 10 vertex
                {0,1,0,0,0,1,1,0,0,0},
                {1,0,0,0,0,0,0,1,0,0},
                {0,0,0,1,0,0,0,1,1,0},
                {0,0,1,0,1,1,1,0,0,1},
                {0,0,0,1,0,0,0,0,1,0},
                {1,0,0,1,0,0,0,1,0,0},
                {1,0,0,1,0,0,0,0,0,0},
                {0,1,1,0,0,1,0,0,0,0},
                {0,0,1,0,1,0,0,0,0,1},
                {0,0,0,1,0,0,0,0,1,0}
        };*/
        //System.out.println(graph);
        graph = new AdjacencyMatrixGraph(matrix, matrix.length, 10);
        int[][] edgesListGraph = GraphGeneration.convertAdjacencyMatrixToEdgesList(graph);
        graph.setAmountOfEdges(edgesListGraph.length);
        System.out.println(graph);
        System.out.println("Edges: " + graph.getAmountOfEdges());
        long start = System.currentTimeMillis();
        GraphCycleFinder.launchCycleFinder(edgesListGraph);
        System.out.println("sec: " + (System.currentTimeMillis() - start));
        GraphCycleCounter cycleCounter4 = new GraphCycleCounter(new AdjacencyMatrixGraph(graph));
        GraphCycleCounter cycleCounter6 = new GraphCycleCounter(graph);
        //GraphCycleCounter cycleCounter8 = new GraphCycleCounter(new AdjacencyMatrixGraph(graph));
        start = System.currentTimeMillis();
        //System.out.println("Amount of 4-Cycles: " + cycleCounter4.countAmountCyclesWithLength(4));
        System.out.println("sec: " + (System.currentTimeMillis() - start));
        System.out.println("--------------------------------------");
        start = System.currentTimeMillis();
        System.out.println("Amount of 6-Cycles: " + cycleCounter6.countAmountCyclesWithLength(6));
        System.out.println("sec: " + (System.currentTimeMillis() - start));
        //System.out.println("Amount of 8-Cycles: " + cycleCounter8.countAmountCyclesWithLength(8));


    }




}