package ru.otus.danilchenko.algorithms.lesson8;

import ru.otus.danilchenko.algorithms.sort.IComparator;
import ru.otus.danilchenko.algorithms.sort.ISort;
import ru.otus.danilchenko.algorithms.sort.ISwap;

import java.util.Objects;

public class QuickSort<T> implements ISort<T> {
    private final IComparator<T> comparator;
    private final ISwap<T> swapper;

    public QuickSort(IComparator<T> comparator, ISwap<T> swapper) {
        Objects.requireNonNull(comparator);
        Objects.requireNonNull(swapper);
        this.comparator = comparator;
        this.swapper = swapper;
    }

    private void qsort(T[] arr, int leftIndex, int rightIndex) {
        if (leftIndex >= rightIndex) {
            return;
        }
        int middleIndex = split(arr, leftIndex, rightIndex);
        qsort(arr, leftIndex, middleIndex - 1);
        qsort(arr, middleIndex + 1, rightIndex);
    }

    private int split(T[] arr, int leftIndex, int rightIndex) {
        int middleIndex = leftIndex - 1;
        T pivot = arr[rightIndex];

        for (int i = leftIndex; i <= rightIndex; i++) {
            if (comparator.compare(pivot, arr[i]) >= 0) {
                middleIndex++;
                if (middleIndex != i) {
                    swapper.swap(arr, middleIndex, i);
                }
            }
        }
        return middleIndex;
    }

    @Override
    public T[] sort(T[] arr) {
        Objects.requireNonNull(arr);
        qsort(arr, 0, arr.length - 1);
        return arr;
    }
}
