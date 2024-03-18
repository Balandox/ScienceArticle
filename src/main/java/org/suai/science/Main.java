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
            graph = GraphGeneration.generateGraph(5);
            dfsCalculation.setGraph(graph);
            isGraphFullyConnected = dfsCalculation.isFullyConnectedGraph();
        }
        while (!isGraphFullyConnected);
        System.out.println(graph);

        GraphCycleCounter cycleCounter = new GraphCycleCounter(graph);
        System.out.println("Amount of 4-Cycles: " + cycleCounter.countAmountCyclesWithLength(4));

    }


}