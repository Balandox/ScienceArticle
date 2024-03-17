package org.suai.science.model;

public class AdjacencyMatrixGraph {

    private int[][] matrix;

    private int amountOfVertex;

    private int amountOfEdges;

    public AdjacencyMatrixGraph(int[][] matrix, int amountOfVertex, int amountOfEdges) {
        this.matrix = matrix;
        this.amountOfVertex = amountOfVertex;
        this.amountOfEdges = amountOfEdges;
    }

    public int[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(int[][] matrix) {
        this.matrix = matrix;
    }

    public int getAmountOfVertex() {
        return amountOfVertex;
    }

    public void setAmountOfVertex(int amountOfVertex) {
        this.amountOfVertex = amountOfVertex;
    }

    public int getAmountOfEdges() {
        return amountOfEdges;
    }

    public void setAmountOfEdges(int amountOfEdges) {
        this.amountOfEdges = amountOfEdges;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                sb.append(matrix[i][j]).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
