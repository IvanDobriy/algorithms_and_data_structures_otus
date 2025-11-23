package ru.otus.danilchenko.algorithms.lesson4.dynamic_arrays;

public class FactorArray<T> implements IArray<T> {
    private static int FACTOR = 2;
    private T[] container;

    FactorArray(int size) {
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
        int multiplyFactor = diff / FACTOR;
        int size = container.length * multiplyFactor == 0 ? 1 : multiplyFactor * FACTOR;
        T[] newArray = ArrayUtils.createArray(size);
        ArrayUtils.copyArray(container, newArray);
        container = newArray;
    }

    @Override
    public T remove(int index) {
        if (index < 0) {
            throw new IllegalArgumentException("index must be positive");
        }
        if (index < container.length) {
            T result = container[index];
            container[index] = null;
            return result;
        }
        return null;
    }
}
