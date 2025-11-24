package ru.otus.danilchenko.algorithms.lesson4.dynamic_arrays;

import java.util.Objects;

public class TwoDimensionallyArray<T> {
    private int rowSize;
    private int colSize;
    private Object[][] array;


    TwoDimensionallyArray(int rowSize, int colSize) {
        if (rowSize < 0) {
            throw new IllegalArgumentException("row size must be positive");
        }
        if (colSize < 0) {
            throw new IllegalArgumentException("col size must be positive");
        }
        array = new Object[rowSize][0];
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
        if (array[rowIndex].length == 0) {
            array[rowIndex] = new Object[colSize];
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
        if (array[rowIndex].length == 0) {
            array[rowIndex] = new Object[colSize];
        }
        return (T) array[rowIndex][colIndex];
    }

    public void copy(int fromRowIndex, TwoDimensionallyArray<T> from, int rowSize, int toRowIndex) {
        Objects.requireNonNull(from);
        if (fromRowIndex < 0) {
            throw new IllegalArgumentException("from row index must be positive");
        }
        if (toRowIndex < 0) {
            throw new IllegalArgumentException("to row index must be positive");
        }
        if (rowSize < 0) {
            throw new IllegalArgumentException("row size must be positive");
        }
        for (int i = 0; i < rowSize; i++) {
            array[toRowIndex + i] = from.array[fromRowIndex];
        }
    }

    public int getRowSize() {
        return rowSize;
    }

    public int getColSize() {
        return colSize;
    }
}
