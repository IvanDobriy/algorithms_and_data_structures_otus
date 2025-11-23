package ru.otus.danilchenko.algorithms.lesson4.dynamic_arrays;

public class MatrixArray<T> implements IArray<T> {
    private static final int PARTITION_SIZE = 10;
    private T[][] container;

    MatrixArray(int size) {
        int linkSize = size / PARTITION_SIZE + 1;
        container = ArrayUtils.createArray(linkSize);
        for (int i = 0; i < container.length; i++) {
            container[i] = ArrayUtils.createArray(PARTITION_SIZE);
        }
    }

    @Override
    public void add(T item, int index) {
        if (index < 0) {
            throw new IllegalArgumentException("index must be positive");
        }
        int containerSize = container.length * PARTITION_SIZE;
        if (index < containerSize) {
            T[] partition = container[index / PARTITION_SIZE];
            partition[index % PARTITION_SIZE] = item;
            return;
        }
        int factor = index / PARTITION_SIZE;
        T[][] newContainer = ArrayUtils.createArray(factor + 1);
        ArrayUtils.copyArray(container, newContainer);
        T[] partition = ArrayUtils.createArray(PARTITION_SIZE);
        partition[index % PARTITION_SIZE] = item;
        newContainer[factor] = partition;
    }

    @Override
    public T remove(int index) {
        if (index < 0) {
            throw new IllegalArgumentException("index must be positive");
        }
        int containerSize = container.length * PARTITION_SIZE;
        if (index >= containerSize) {
            return null;
        }
        T[] partition = container[index / PARTITION_SIZE];
        int partitionPosition = index % PARTITION_SIZE;
        T result = partition[partitionPosition];
        partition[partitionPosition] = null;
        return result;
    }

    @Override
    public T get(int index) {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }
}
