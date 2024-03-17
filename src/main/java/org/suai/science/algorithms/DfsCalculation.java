package org.suai.science.algorithms;

import org.suai.science.model.AdjacencyMatrixGraph;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Random;

public class DfsCalculation {

    private AdjacencyMatrixGraph graph;

    private final Random random = new Random();

    public DfsCalculation(AdjacencyMatrixGraph graph) {
        this.graph = graph;
    }

    public DfsCalculation(){}

    public boolean isFullyConnectedGraph() {
        int start = random.nextInt(graph.getAmountOfVertex());
        boolean isFullyConnectedGraph = this.dfs(start);
        return isFullyConnectedGraph;
    }

    public boolean dfs(int start){
        boolean[] visited = new boolean[graph.getAmountOfVertex()];
        Deque<Integer> stack = new ArrayDeque<>();
        int[][] matrix = graph.getMatrix();

        visited[start] = true;
        stack.addFirst(start);
        while (!stack.isEmpty()){
            // Извлекаем вершину из стэка и добавляем в ход обхода
            int node = stack.removeFirst();
            int[] neighbors = matrix[node];

            if (!isIsolatedVertex(neighbors)) {
                for (int i = 0; i < neighbors.length; i++) {
                    if (neighbors[i] == 1 && !visited[i]) {
                        visited[i] = true;
                        stack.addFirst(i);
                    }
                }
            }
            else{
                return false;
            }
        }
        for (boolean v : visited)
            if (!v) return false;

        return true;
    }

    public AdjacencyMatrixGraph getGraph() {
        return graph;
    }

    public boolean isIsolatedVertex(int[] matrixRow){
        return Arrays.stream(matrixRow).noneMatch((elem) -> elem == 1);
    }

    public void setGraph(AdjacencyMatrixGraph graph) {
        this.graph = graph;
    }


}
