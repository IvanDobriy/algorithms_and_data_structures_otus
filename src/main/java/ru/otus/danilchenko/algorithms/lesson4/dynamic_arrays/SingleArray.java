package ru.otus.danilchenko.algorithms.lesson4.dynamic_arrays;

public class SingleArray<T> implements IArray<T> {
    private T[] container;

    private T[] createArray(int size) {
        return (T[]) (new Object[size]);
    }

    private void copyArray(T[] from, T[] to) {
        if (from.length > to.length) {
            throw new IllegalArgumentException("from.length must be more than to.length");
        }
        for (int i = 0; i < from.length; i++) {
            to[i] = from[i];
        }
    }

    SingleArray(int size) {
        container = createArray(size);
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
        T[] newArray = createArray(index + 1);
        copyArray(container, newArray);
        container[index] = item;
    }


    @Override
    public T remove(int index) {
        if (index >= container.length) {
            return null;
        }
        T result = container[index];
        container[index] = null;
        return result;
    }
}
