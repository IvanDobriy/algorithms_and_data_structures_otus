package ru.otus.danilchenko.algorithms.lesson16.sceleton.sort;

import ru.otus.danilchenko.algorithms.lesson4.dynamic_arrays.IArray;
import ru.otus.danilchenko.algorithms.sort.IComparator;

import java.util.Objects;

public class NQuickSort<T> implements INSort<T> {
    private final IComparator<T> comparator;
    private final INSwap<T> swapper;

    public NQuickSort(IComparator<T> comparator, INSwap<T> swapper) {
        Objects.requireNonNull(comparator);
        Objects.requireNonNull(swapper);
        this.comparator = comparator;
        this.swapper = swapper;
    }

    private void qsort(IArray<T> arr, int leftIndex, int rightIndex) {
        if (leftIndex >= rightIndex) {
            return;
        }
        int middleIndex = split(arr, leftIndex, rightIndex);
        qsort(arr, leftIndex, middleIndex - 1);
        qsort(arr, middleIndex + 1, rightIndex);
    }

    private int split(IArray<T> arr, int leftIndex, int rightIndex) {
        int middleIndex = leftIndex - 1;
        T pivot = arr.get(rightIndex);

        for (int i = leftIndex; i <= rightIndex; i++) {
            if (comparator.compare(pivot, arr.get(i)) >= 0) {
                middleIndex++;
                if (middleIndex != i) {
                    swapper.swap(arr, middleIndex, i);
                }
            }
        }
        return middleIndex;
    }

    @Override
    public IArray<T> sort(IArray<T> arr) {
        Objects.requireNonNull(arr);
        qsort(arr, 0, arr.size() - 1);
        return arr;
    }
}
