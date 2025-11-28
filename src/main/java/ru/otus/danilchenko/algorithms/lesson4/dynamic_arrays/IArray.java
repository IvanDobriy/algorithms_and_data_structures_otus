package ru.otus.danilchenko.algorithms.lesson4.dynamic_arrays;

public interface IArray<T> {
    void add(T item, int index);

    T remove(int index);


    T get(int index);

    int size();
}
