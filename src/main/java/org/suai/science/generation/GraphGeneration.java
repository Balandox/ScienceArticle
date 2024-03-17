package org.suai.science.generation;

import org.suai.science.model.AdjacencyMatrixGraph;

import java.util.Random;

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

}
