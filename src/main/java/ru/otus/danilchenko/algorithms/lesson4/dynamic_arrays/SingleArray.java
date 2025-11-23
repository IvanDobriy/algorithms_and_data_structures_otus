package ru.otus.danilchenko.algorithms.lesson4.dynamic_arrays;

public class SingleArray<T> implements IArray<T> {
    private T[] container;

    SingleArray(int size) {
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
        T[] newArray = ArrayUtils.createArray(index + 1);
        ArrayUtils.copyArray(container, newArray);
        container[index] = item;
    }


    @Override
    public T remove(int index) {
        if (index < 0) {
            throw new IllegalArgumentException("index must be positive");
        }
        if (index >= container.length) {
            return null;
        }
        T result = container[index];
        container[index] = null;
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
