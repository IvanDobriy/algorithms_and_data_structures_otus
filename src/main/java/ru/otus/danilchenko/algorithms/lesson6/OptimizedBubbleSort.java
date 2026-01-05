package ru.otus.danilchenko.algorithms.lesson6;

import ru.otus.danilchenko.algorithms.sort.IComparator;
import ru.otus.danilchenko.algorithms.sort.ISort;
import ru.otus.danilchenko.algorithms.sort.ISwap;

import java.util.Objects;

public class OptimizedBubbleSort<T> implements ISort<T> {
    private final IComparator<T> comparator;
    private final ISwap<T> swapper;

    public OptimizedBubbleSort(IComparator<T> comparator, ISwap<T> swapper) {
        Objects.requireNonNull(comparator);
        Objects.requireNonNull(swapper);
        this.comparator = comparator;
        this.swapper = swapper;
    }

    @Override
    public T[] sort(T[] arr) {
        Objects.requireNonNull(arr);
        boolean isSwapped;
        for (int i = 0; i < arr.length - 1; i++) {
            isSwapped = false;
            for (int j = arr.length - 1; j > i; j--) {
                if (comparator.compare(arr[j - 1], arr[j]) > 0) {
                    isSwapped = true;
                    swapper.swap(arr, j - 1, j);
                }
            }
            if (!isSwapped) {
                break;
            }
        }
        return arr;
    }
}
