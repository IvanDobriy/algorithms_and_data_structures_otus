package ru.otus.danilchenko.algorithms.lesson6;

import ru.otus.danilchenko.algorithms.lesson3.Pow;

import java.util.Objects;

public class FrankAndLazarusShellSort<T> implements ISort<T> {
    private final IComparator<T> comparator;
    private final ISwap<T> swapper;

    public FrankAndLazarusShellSort(IComparator<T> comparator, ISwap<T> swapper) {
        Objects.requireNonNull(comparator);
        Objects.requireNonNull(swapper);
        this.comparator = comparator;
        this.swapper = swapper;
    }

    @Override
    public T[] sort(T[] arr) {
        Objects.requireNonNull(arr);
        Objects.requireNonNull(arr);
        if (arr.length == 1) {
            return arr;
        }
        int gap = arr.length;
        for (int k = 1; gap > 1; k++) {
            gap = 2 * (gap / (int) Pow.bin(2, k + 1)) + 1;
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
