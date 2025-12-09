package ru.otus.danilchenko.algorithms.lesson6;

import ru.otus.danilchenko.algorithms.lesson6.metrics.IExchangeCounter;

import java.util.Objects;

public class BinarySearchInsertionSort<T> implements ISort<T> {
    private final IComparator<T> comparator;
    private final IExchangeCounter exchangeCounter;
    private final IComparator<Integer> indexComparator;

    public BinarySearchInsertionSort(IComparator<T> comparator, IComparator<Integer> indexComparator, IExchangeCounter exchangeCounter) {
        Objects.requireNonNull(comparator);
        Objects.requireNonNull(exchangeCounter);
        Objects.requireNonNull(indexComparator);
        this.comparator = comparator;
        this.exchangeCounter = exchangeCounter;
        this.indexComparator = indexComparator;
    }

    private int binarySearch(T[] arr, int left, int right, T key) {
        int mid = -1;
        while (indexComparator.compare(left, right) <= 0) {
            mid = (left + right) / 2;
            if (comparator.compare(key, arr[mid]) == 0) {
                return mid;
            }
            if (comparator.compare(key, arr[mid]) < 0) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return mid;
    }

    @Override
    public T[] sort(T[] arr) {
        Objects.requireNonNull(arr);
        T tmp;
        for (int i = 1; i < arr.length; i++) {
            tmp = arr[i];
            int pos = binarySearch(arr, 0, i - 1, tmp);
            for (int j = i - 1; j >= pos; j--) {
                exchangeCounter.count(1);
                arr[j + 1] = arr[j];
            }
            exchangeCounter.count(1);
            arr[pos] = tmp;
        }
        return arr;
    }
}
