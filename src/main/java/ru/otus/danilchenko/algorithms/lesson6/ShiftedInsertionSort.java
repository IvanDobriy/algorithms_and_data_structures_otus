package ru.otus.danilchenko.algorithms.lesson6;

import ru.otus.danilchenko.algorithms.lesson6.metrics.IExchangeCounter;

import java.util.Objects;

public class ShiftedInsertionSort<T> implements ISort<T> {
    private final IComparator<T> comparator;
    private final IExchangeCounter exchangeCounter;


    public ShiftedInsertionSort(IComparator<T> comparator, IExchangeCounter exchangeCounter) {
        Objects.requireNonNull(comparator);
        Objects.requireNonNull(exchangeCounter);
        this.comparator = comparator;
        this.exchangeCounter = exchangeCounter;
    }

    @Override
    public T[] sort(T[] arr) {
        Objects.requireNonNull(arr);
        int j;
        T tmp;
        for (int i = 1; i < arr.length; i++) {
            exchangeCounter.count(1);
            tmp = arr[i];
            for (j = i - 1; j >= 0; j--) {
                if (comparator.compare(arr[j], tmp) > 0) {
                    exchangeCounter.count(1);
                    arr[j + 1] = arr[j];
                    continue;
                }
                break;
            }
            exchangeCounter.count(1);
            arr[j + 1] = tmp;
        }
        return arr;
    }
}
