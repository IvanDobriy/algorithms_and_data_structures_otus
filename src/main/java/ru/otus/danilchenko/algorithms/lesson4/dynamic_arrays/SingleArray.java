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
            return null;
        }
        T result = container[index];
        container[index] = null;
        return result;
    }

    @Override
    public T get(int index) {
        if(index < 0){
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
