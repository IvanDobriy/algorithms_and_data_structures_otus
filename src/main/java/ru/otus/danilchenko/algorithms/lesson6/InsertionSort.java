package ru.otus.danilchenko.algorithms.lesson6;

import java.util.Objects;

public class InsertionSort<T> implements ISort<T> {
    private final IComparator<T> comparator;
    private final ISwap<T> swapper;

    public InsertionSort(IComparator<T> comparator, ISwap<T> swapper) {
        Objects.requireNonNull(comparator);
        Objects.requireNonNull(swapper);
        this.comparator = comparator;
        this.swapper = swapper;
    }

    @Override
    public T[] sort(T[] arr) {
        Objects.requireNonNull(arr);
        for (int i = 1; i < arr.length; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (comparator.compare(arr[j], arr[j + 1]) > 0) {
                    swapper.swap(arr, j, j + 1);
                    continue;
                }
                break;
            }
        }
        return arr;
    }
}
