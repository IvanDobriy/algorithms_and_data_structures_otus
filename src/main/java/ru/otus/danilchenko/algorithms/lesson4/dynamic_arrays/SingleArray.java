package ru.otus.danilchenko.algorithms.lesson4.dynamic_arrays;

public class SingleArray<T> implements IArray<T> {
    private T[] container;

    public SingleArray(int size) {
        container = ArrayUtils.createArray(size);
    }

    @Override
    public void add(T item, int index) {
        if (index < 0) {
            throw new IllegalArgumentException("index must be positive");
        }
        if (index < container.length) {
            T[] newArray = ArrayUtils.createArray(container.length + 1);
            if (index == 0) {
                System.arraycopy(container, 0, newArray, 1, container.length);
                newArray[index] = item;
                container = newArray;
                return;
            }
            System.arraycopy(container, 0, newArray, 0, index + 1);
            newArray[index] = item;
            System.arraycopy(container, index, newArray, index + 1, container.length - index);
            container = newArray;
            return;
        }
        T[] newArray = ArrayUtils.createArray(index + 1);
        System.arraycopy(container, 0, newArray, 0, container.length);
        newArray[index] = item;
        container = newArray;
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
        T[] newArray = ArrayUtils.createArray(container.length - 1);
        if (index == 0) {
            System.arraycopy(container, index + 1, newArray, index, container.length - 1);
            container = newArray;
            return result;
        }
        if (index == container.length - 1) {
            System.arraycopy(container, 0, newArray, 0, index);
            container = newArray;
            return result;
        }
        System.arraycopy(container, 0, newArray, 0, index);
        System.arraycopy(container, index + 1, newArray, index, newArray.length - index);
        container = newArray;
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
        return container.length;
    }
}
