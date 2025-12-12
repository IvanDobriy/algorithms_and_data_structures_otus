package ru.otus.danilchenko.algorithms.lesson7;


import ru.otus.danilchenko.algorithms.sort.IComparator;
import ru.otus.danilchenko.algorithms.sort.ISort;
import ru.otus.danilchenko.algorithms.sort.ISwap;

import java.util.Objects;

public class HeapSort<T> implements ISort<T> {
    private final IComparator<T> comparator;
    private final ISwap<T> swapper;

    public HeapSort(IComparator comparator, ISwap swapper) {
        Objects.requireNonNull(comparator);
        Objects.requireNonNull(swapper);
        this.comparator = comparator;
        this.swapper = swapper;
    }

    @Override
    public T[] sort(T[] arr) {
        Objects.requireNonNull(arr);

        return arr;
    }
}
