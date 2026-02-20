package ru.otus.danilchenko.algorithms.lesson4.dynamic_arrays;

public interface IArray<T> {
    void set(T item, int index);
    void add(T item, int index);

    T remove(int index);


    T get(int index);

    int size();
}
