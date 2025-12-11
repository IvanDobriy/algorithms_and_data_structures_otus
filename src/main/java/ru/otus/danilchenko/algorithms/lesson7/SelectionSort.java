package ru.otus.danilchenko.algorithms.lesson7;

import ru.otus.danilchenko.algorithms.sort.IComparator;
import ru.otus.danilchenko.algorithms.sort.ISort;

import java.util.Objects;

public class SelectionSort<T> implements ISort<T> {
    private final IComparator<T> comparator;

    public SelectionSort(IComparator<T> comparator) {
        Objects.requireNonNull(comparator);
        this.comparator = comparator;
    }


    @Override
    public T[] sort(T[] arr) {
        Objects.requireNonNull(arr);


        return arr;
    }
}
