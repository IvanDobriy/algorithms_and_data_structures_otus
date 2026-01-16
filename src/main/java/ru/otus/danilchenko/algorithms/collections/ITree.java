package ru.otus.danilchenko.algorithms.collections;

public interface ITree<T> {
    void insert(T value);

    boolean search(T value);

    void remove(T value);

    T[] toArray();
}
