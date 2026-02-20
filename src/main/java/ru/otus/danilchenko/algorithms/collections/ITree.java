package ru.otus.danilchenko.algorithms.collections;

import ru.otus.danilchenko.algorithms.lesson4.dynamic_arrays.SingleArray;

public interface ITree<T> {
    void insert(T value);

    boolean search(T value);

    void remove(T value);

    SingleArray<T> toArray();
}
