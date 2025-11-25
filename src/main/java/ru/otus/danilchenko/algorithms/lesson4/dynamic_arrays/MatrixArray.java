package ru.otus.danilchenko.algorithms.lesson4.dynamic_arrays;

public class MatrixArray<T> implements IArray<T> {
    private static final int PARTITION_SIZE = 10;
    private TwoDimensionallyArray<T> container;
    private int size;

    private int calculateColumnIndex(int index) {
        return index % PARTITION_SIZE;
    }

    private int calculateRowIndex(int index) {
        return index / PARTITION_SIZE;
    }

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
        return container.get(calculateRowIndex(index), calculateColumnIndex(index));
    }

    @Override
    public int size() {
        return size;
    }
}
