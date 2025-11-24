package ru.otus.danilchenko.algorithms.lesson4.dynamic_arrays;

public class TwoDimensionallyArray<T> {
    private final int rowSize;
    private final int colSize;
    private final Object[][] array;

    TwoDimensionallyArray(int rowSize, int colSize) {
        if (rowSize < 0) {
            throw new IllegalArgumentException("row size must be positive");
        }
        if (colSize < 0) {
            throw new IllegalArgumentException("col size must be positive");
        }
        array = new Object[rowSize][colSize];
        this.rowSize = rowSize;
        this.colSize = colSize;
    }

    public void set(int rowIndex, int colIndex, T value) {
        if (rowIndex < 0) {
            throw new IllegalArgumentException("row index must be positive");
        }
        if (colIndex < 0) {
            throw new IllegalArgumentException("col index must positive");
        }
        array[rowIndex][colIndex] = value;
    }

    public T get(int rowIndex, int colIndex) {
        if (rowIndex < 0) {
            throw new IllegalArgumentException("row index must be positive");
        }
        if (colIndex < 0) {
            throw new IllegalArgumentException("col index must positive");
        }
        return (T) array[rowIndex][colIndex];
    }

    public int getRowSize() {
        return rowSize;
    }

    public int getColSize() {
        return colSize;
    }

}
