package ru.otus.danilchenko.algorithms.lesson6;

import ru.otus.danilchenko.algorithms.sort.IComparator;
import ru.otus.danilchenko.algorithms.sort.ISort;
import ru.otus.danilchenko.algorithms.sort.ISwap;

import java.util.Objects;

public class ShellSort<T> implements ISort<T> {
    private final IComparator<T> comparator;
    private final ISwap<T> swapper;

    public ShellSort(IComparator<T> comparator, ISwap<T> swapper) {
        Objects.requireNonNull(comparator);
        Objects.requireNonNull(swapper);
        this.comparator = comparator;
        this.swapper = swapper;
    }

    @Override
    public T[] sort(T[] arr) {
        Objects.requireNonNull(arr);
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int j = gap; j < arr.length; j++) {
                for (int i = j; i >= gap; i -= gap) {
                    if (comparator.compare(arr[i - gap], arr[i]) > 0) {
                        swapper.swap(arr, i - gap, i);
                        continue;
                    }
                    break;
                }
            }
        }
        return arr;
    }
}
