package ru.otus.danilchenko.algorithms.lesson4.dynamic_arrays;

public class FactorArray<T> implements IArray<T> {
    private static int FACTOR = 2;
    private T[] container;
    private int size;

    public FactorArray(int size) {
        if (size < 0) {
            throw new IllegalArgumentException("size must be positive");
        }
        this.size = size;
        container = ArrayUtils.createArray(size);
    }

    @Override
    public void set(T item, int index) {
        container[index] = item;
    }

    @Override
    public void add(T item, int index) {
        if (index < 0) {
            throw new IllegalArgumentException("index must be positive");
        }
        if (index < size) {
            if (size >= container.length) {
                T[] newArray = ArrayUtils.createArray(container.length * FACTOR);
                System.arraycopy(container, 0, newArray, 0, container.length);
                container = newArray;
            }

            for (int i = size - 1; i >= index; i--) {
                container[i + 1] = container[i];
            }
            container[index] = item;
            size++;
            return;
        }
        int diff = index - size;
        int multiplyFactor = diff / FACTOR + 1;
        int newSize = size * multiplyFactor * FACTOR;
        newSize = newSize == 0 ? 1 : newSize;
        if (newSize > container.length) {
            T[] newArray = ArrayUtils.createArray(newSize);
            System.arraycopy(container, 0, newArray, 0, container.length);
            container = newArray;
        }
        container[index] = item;
        size = index + 1;
    }

    @Override
    public T remove(int index) {
        if (index < 0) {
            throw new IllegalArgumentException("index must be positive");
        }
        if (index >= container.length) {
            throw new IllegalArgumentException("out of range");
        }
        T result = container[index];
        System.arraycopy(container, index + 1, container, index, size - index - 1);
        size--;
        if (container.length - size > size * FACTOR) {
            T[] newArray = ArrayUtils.createArray(container.length - container.length / FACTOR);
            System.arraycopy(container, 0, newArray, 0, size);
            container = newArray;
        }
        return result;
    }

    @Override
    public T get(int index) {
        if (index < 0) {
            throw new IllegalArgumentException("index must be positive");
        }
        if (index >= container.length) {
            throw new IllegalArgumentException("out of range");
        }
        return container[index];
    }

    @Override
    public int size() {
        return size;
    }
}
