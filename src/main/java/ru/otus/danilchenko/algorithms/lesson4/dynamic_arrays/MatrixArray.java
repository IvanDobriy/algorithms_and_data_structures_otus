package ru.otus.danilchenko.algorithms.lesson4.dynamic_arrays;

public class MatrixArray<T> implements IArray<T> {
    private static final int PARTITION_SIZE = 10;
    private TwoDimensionallyArray<T> container;
    private int size;

    MatrixArray(int size) {
        int linkSize = size / PARTITION_SIZE + 1;
        container = new TwoDimensionallyArray<>(linkSize, PARTITION_SIZE);
        this.size = size;
    }

    @Override
    public void add(T item, int index) {
        if (index < 0) {
            throw new IllegalArgumentException("index must be positive");
        }
        int containerSize = container.getRowSize() * container.getColSize();
        if (index < containerSize) {
            container.set(index / PARTITION_SIZE, index % PARTITION_SIZE, item);
            return;
        }
        int factor = index / PARTITION_SIZE;

        TwoDimensionallyArray<T> newArray = new TwoDimensionallyArray<>(factor + 1, PARTITION_SIZE);
        newArray.copy(0, container, 0, container.getRowSize());
        newArray.set(index / PARTITION_SIZE, index % PARTITION_SIZE, item);
        container = newArray;
    }

    @Override
    public T remove(int index) {
        if (index < 0) {
            throw new IllegalArgumentException("index must be positive");
        }
//        int containerSize = container.length * PARTITION_SIZE;
//        if (index >= containerSize) {
//            return null;
//        }
//        T[] partition = container[index / PARTITION_SIZE];
//        int partitionPosition = index % PARTITION_SIZE;
//        T result = partition[partitionPosition];
//        partition[partitionPosition] = null;
        return null;
    }

    @Override
    public T get(int index) {
        return container.get(index / PARTITION_SIZE, index % PARTITION_SIZE);
    }

    @Override
    public int size() {
        return size;
    }
}
