package org.suai.science.generation;

import org.suai.science.model.AdjacencyMatrixGraph;

import java.util.*;

public class GraphGeneration {

    public static AdjacencyMatrixGraph generateGraph(int numVertices) {
        Random random = new Random();
        int[][] adjacencyMatrix = new int[numVertices][numVertices];
        int amountOfEdges = 0;

        // Заполнение матрицы смежности случайными значениями
        for (int i = 0; i < numVertices; i++) {
            for (int j = i + 1; j < numVertices; j++) {
                int randomValue = random.nextInt(2); // Генерируем случайное значение 0 или 1
                if(randomValue == 1)
                    amountOfEdges++;
                adjacencyMatrix[i][j] = randomValue;
                adjacencyMatrix[j][i] = randomValue; // Матрица симметрична
            }
        }
        return new AdjacencyMatrixGraph(adjacencyMatrix, numVertices, amountOfEdges);
    }

    public static int[][] convertAdjacencyMatrixToEdgesList(AdjacencyMatrixGraph graph){
        int[][] matrix = graph.getMatrix();
        List<int[]> edgesList = new ArrayList<>();
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[i].length; j++){
                if(matrix[i][j] == 1 && !isAlreadyInList(edgesList, i, j))
                    edgesList.add(new int[] {i, j});
            }
        }
        return edgesList.toArray(new int[0][0]);
    }

    private static boolean isAlreadyInList(List<int[]> edgesList, int i, int j){
        for(int[] tmp : edgesList){
            if((tmp[0] == i && tmp[1] == j) || (tmp[0] == j && tmp[1] == i))
                return true;
        }
        return false;
    }

}
