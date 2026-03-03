package ru.otus.danilchenko.algorithms.lesson14.performance.adjacency;

import ru.otus.danilchenko.algorithms.lesson4.dynamic_arrays.IArray;
import ru.otus.danilchenko.algorithms.lesson4.dynamic_arrays.SingleArray;

public class AdjacencyMatrix {
    private IArray<Integer> container;
    private int vertexSize;

    public AdjacencyMatrix(int vertexSize) {
        container = new SingleArray<>(vertexSize * vertexSize);
        this.vertexSize = vertexSize;
    }

    private int calcIndex(int row, int col) {
        return row * vertexSize + col;
    }

    public void set(int row, int col, int value) {
        final int size = container.size();
        if (col > size) {
            throw new IllegalArgumentException("col > col size");
        }
        if (row > size) {
            throw new IllegalArgumentException("row > row size");
        }
        container.set(value, calcIndex(row, col));
    }

    public void set(int row, int col){
        set(row, col, 1);
        set(col, row, 1);
    }

    public int get(int row, int col) {
        final int size = container.size();
        if (col > size) {
            throw new IllegalArgumentException("col > col size");
        }
        if (row > size) {
            throw new IllegalArgumentException("row > row size");
        }
        final Integer result = container.get(calcIndex(row, col));
        if(result == null){
            return 0;
        }
        return result;
    }
}
