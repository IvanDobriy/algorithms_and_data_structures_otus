package ru.otus.danilchenko.algorithms.lesson4.dynamic_arrays;

import java.util.ArrayList;
import java.util.Objects;

public class ArrayListWrapper<T> implements IArray<T> {

    private ArrayList<T> list;

    public ArrayListWrapper(ArrayList<T> list) {
        Objects.requireNonNull(list);
        this.list = list;
    }

    @Override
    public void set(T item, int index) {
       list.set(index, item);
    }

    @Override
    public void add(T item, int index) {
        list.add(index, item);
    }

    @Override
    public T remove(int index) {
        return list.remove(index);
    }

    @Override
    public T get(int index) {
        return list.get(index);
    }

    @Override
    public int size() {
        return list.size();
    }
}
