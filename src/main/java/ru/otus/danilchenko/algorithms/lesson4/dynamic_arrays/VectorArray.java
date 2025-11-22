package ru.otus.danilchenko.algorithms.lesson4.dynamic_arrays;

public class VectorArray<T> implements IArray<T> {
    private static final int VECTOR_LENGTH = 10;
    private T[] container;

    VectorArray(int size) {
        if (size < 0) {
            throw new IllegalArgumentException("size must be positive");
        }
        container = ArrayUtils.createArray(size);
    }

    @Override
    public void add(T item, int index) {
        if (index < 0) {
            throw new IllegalArgumentException("index must be positive");
        }
        if (index < container.length) {
            container[index] = item;
            return;
        }
        int diff = index - container.length;
        int multiplyFactor = diff / VECTOR_LENGTH;
        int size = container.length + (multiplyFactor == 0 ? VECTOR_LENGTH : multiplyFactor * VECTOR_LENGTH);
        T[] newArray = ArrayUtils.createArray(size);
        ArrayUtils.copyArray(container, newArray);
        newArray[index] = item;
    }

    @Override
    public T remove(int index) {
        if (index < 0) {
            throw new IllegalArgumentException("index must be positive");
        }
        if (index >= container.length) {
            return null;
        }
        return container[index];
    }
}
