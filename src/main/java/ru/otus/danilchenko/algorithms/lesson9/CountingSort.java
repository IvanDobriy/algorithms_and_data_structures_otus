package ru.otus.danilchenko.algorithms.lesson9;

import ru.otus.danilchenko.algorithms.lesson4.dynamic_arrays.SingleArray;
import ru.otus.danilchenko.algorithms.metrics.IExchangeCounter;
import ru.otus.danilchenko.algorithms.sort.IComparator;
import ru.otus.danilchenko.algorithms.sort.ISort;

import java.util.Objects;

public class CountingSort<T> implements ISort<T> {
    private final IAmounter<T> amounter;
    private final IExchangeCounter exchangeCounter;
    private final IComparator<T> comparator;


    public CountingSort(IComparator<T> comparator, IExchangeCounter exchangeCounter, IAmounter<T> amounter) {
        Objects.requireNonNull(amounter);
        Objects.requireNonNull(exchangeCounter);
        this.amounter = amounter;
        this.exchangeCounter = exchangeCounter;
        this.comparator = comparator;
    }

    private int findMax(T[] arr) {
        T max = arr[0];
        T el;
        for (int i = 1; i < arr.length; i++) {
            el = arr[i];
            if (comparator.compare(max, el) < 0) {
                max = el;
            }
        }
        return amounter.getAmount(max);
    }

    @Override
    public T[] sort(T[] arr) {
        Objects.requireNonNull(arr);
        if (arr.length == 0) {
            return arr;
        }
        final SingleArray<T> result = new SingleArray<>(arr.length);
        int maxAmount = findMax(arr);
        int[] counter = new int[maxAmount + 1];
        int amount;

        for (T t : arr) {
            exchangeCounter.count(2);
            amount = amounter.getAmount(t);
            counter[amount]++;
        }
        for (int i = 1; i < counter.length; i++) {
            exchangeCounter.count(1);
            counter[i] += counter[i - 1];
        }
        int pos;
        T el;
        for (int i = arr.length - 1; i >= 0; i--) {
            exchangeCounter.count(5);
            el = arr[i];
            amount = amounter.getAmount(el);
            pos = counter[amount] - 1;
            counter[amount] = pos;
            result.set(el, pos);
        }
        for (int i = 0; i < result.size(); i++) {
            exchangeCounter.count(1);
            arr[i] = result.get(i);
        }
        return arr;
    }
}
