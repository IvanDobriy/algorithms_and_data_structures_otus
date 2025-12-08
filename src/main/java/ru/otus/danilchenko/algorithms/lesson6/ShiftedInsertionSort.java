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
        for (int i = 1; i < arr.length; i++) {
            for (j = i - 1; j >= 0; j--) {
                exchangeCounter.count(1);
                if (comparator.compare(arr[j], arr[i]) < 0) {
                    exchangeCounter.count(1);
                }
            }

        }
        return arr;
    }
}
