package ru.otus.danilchenko.algorithms.lesson4.dynamic_arrays;

public class MatrixArray<T> implements IArray<T> {
    private static final int PARTITION_SIZE = 1024;
    private TwoDimensionallyArray<T> container;
    private int size;

    private int calculateColumnIndex(int index) {
        return index % PARTITION_SIZE;
    }

    private int calculateRowIndex(int index) {
        return index / PARTITION_SIZE;
    }

    public MatrixArray(int size) {
        int linkSize = size / PARTITION_SIZE + 1;
        container = new TwoDimensionallyArray<>(linkSize, PARTITION_SIZE);
        this.size = size;
    }

    @Override
    public void add(T item, int index) {
        if (index < 0) {
            throw new IllegalArgumentException("index must be positive");
        }
        int containerSize = container.getColSize() * container.getRowSize();
        if (index < size) {
            if (size >= containerSize) {
                TwoDimensionallyArray<T> newArray = new TwoDimensionallyArray<>(container.getRowSize() + 1, container.getColSize());
                newArray.update(0, container, container.getRowSize(), 0);
                container = newArray;
            }
            for (int i = size - 1; i >= index; i--) {
                container.set(
                        calculateRowIndex(i + 1), calculateColumnIndex(i + 1),
                        container.get(calculateRowIndex(i), calculateColumnIndex(i))
                );
            }
            container.set(calculateRowIndex(index), calculateColumnIndex(index), item);
            size++;
            return;
        }
        int diff = index - size;
        int factor = diff / PARTITION_SIZE + 1;
        int expectedContainerSize = size + factor * PARTITION_SIZE;
        if (expectedContainerSize > containerSize) {
            TwoDimensionallyArray<T> newArray = new TwoDimensionallyArray<>(container.getRowSize() + factor, container.getColSize());
            newArray.update(0, container, container.getRowSize(), 0);
            container = newArray;
        }
        container.set(calculateRowIndex(index), calculateColumnIndex(index), item);
        size = index + 1;
    }

    @Override
    public T remove(int index) {
        if (index < 0) {
            throw new IllegalArgumentException("index must be positive");
        }
        if (index >= container.getRowSize() * container.getColSize()) {
            throw new IllegalArgumentException("out of range");
        }
        T result = container.get(calculateRowIndex(index), calculateColumnIndex(index));
        for (int i = 0; i < size - index; i++) {
            container.set(
                    calculateRowIndex(i + index), calculateColumnIndex(i + index),
                    container.get(calculateRowIndex(i + index + 1), calculateColumnIndex(i + index + 1))
            );
        }
        size--;
        if (container.getRowSize() * container.getColSize() - size > 2 * PARTITION_SIZE) {
            TwoDimensionallyArray<T> newArray = new TwoDimensionallyArray<>(container.getRowSize() - 1, container.getColSize());
            newArray.update(0, container, newArray.getRowSize(), 0);
            container = newArray;
        }
        return result;
    }

    @Override
    public T get(int index) {
        return container.get(calculateRowIndex(index), calculateColumnIndex(index));
    }

    @Override
    public int size() {
        return size;
    }
}
