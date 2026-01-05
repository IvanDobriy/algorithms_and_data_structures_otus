package ru.otus.danilchenko.algorithms.lesson7;

import ru.otus.danilchenko.algorithms.sort.IComparator;
import ru.otus.danilchenko.algorithms.sort.ISort;
import ru.otus.danilchenko.algorithms.sort.ISwap;

import java.util.Objects;

public class ShakerSort<T> implements ISort<T> {
    private final IComparator<T> comparator;
    private final ISwap<T> swapper;

    public ShakerSort(IComparator<T> comparator, ISwap<T> swapper) {
        Objects.requireNonNull(comparator);
        Objects.requireNonNull(swapper);
        this.comparator = comparator;
        this.swapper = swapper;
    }

    @Override
    public T[] sort(T[] arr) {
        Objects.requireNonNull(arr);
        int left = 0;
        int right = arr.length - 1;
        boolean isSwapped;
        while (left < right) {
            isSwapped = false;
            for (int i = right; i > left; i--) {
                if (comparator.compare(arr[i], arr[i - 1]) < 0) {
                    isSwapped = true;
                    swapper.swap(arr, i, i - 1);
                }
            }
            left++;
            for (int i = left; i < right; i++) {
                if (comparator.compare(arr[i], arr[i + 1]) > 0) {
                    isSwapped = true;
                    swapper.swap(arr, i, i + 1);
                }
            }
            right--;
            if (!isSwapped) {
                break;
            }
        }
        return arr;
    }
}
