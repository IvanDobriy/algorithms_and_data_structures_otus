package ru.otus.danilchenko.algorithms.lesson14.performance.adjacency_matrix;

import ru.otus.danilchenko.algorithms.lesson4.dynamic_arrays.IArray;
import ru.otus.danilchenko.algorithms.lesson4.dynamic_arrays.SingleArray;

public class AdjacencyMatrix {
    private IArray<Integer> container;
    private int vertexSize;
    private boolean isOriented;

    public AdjacencyMatrix(int vertexSize, boolean isOriented) {
        container = new SingleArray<>(vertexSize * vertexSize);
        this.vertexSize = vertexSize;
        this.isOriented = isOriented;
    }

    private int calcIndex(int row, int col) {
        return row * vertexSize + col;
    }

    private void check(int row, int col) {
        final int size = container.size();
        if (col > size) {
            throw new IllegalArgumentException("col > col size");
        }
        if (row > size) {
            throw new IllegalArgumentException("row > row size");
        }
    }

    private void check(int row, int col, int value) {
        check(row, col);
        if (!isOriented && value < 0) {
            throw new IllegalArgumentException(String.format("graph is unordered but value is negative:%d", value));
        }
    }

    public void set(int row, int col, int value) {
        check(row, col, value);
        container.set(value, calcIndex(row, col));
    }

    public void set(int row, int col) {
        set(row, col, 1);
        set(col, row, 1);
    }

    public int get(int row, int col) {
        check(row, col);
        final Integer result = container.get(calcIndex(row, col));
        if (result == null) {
            return 0;
        }
        return result;
    }
}
