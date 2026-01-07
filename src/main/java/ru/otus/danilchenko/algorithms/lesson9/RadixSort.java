package ru.otus.danilchenko.algorithms.lesson9;

import org.apache.commons.math3.analysis.function.Sin;
import ru.otus.danilchenko.algorithms.lesson4.dynamic_arrays.SingleArray;
import ru.otus.danilchenko.algorithms.metrics.IExchangeCounter;
import ru.otus.danilchenko.algorithms.sort.IComparator;
import ru.otus.danilchenko.algorithms.sort.ISort;

import java.util.Objects;

public class RadixSort<T> implements ISort<T> {
    private final IExchangeCounter exchangeCounter;
    private final IAmounter<T> amounter;
    private final IComparator<T> comparator;

    public RadixSort(IComparator<T> comparator, IExchangeCounter exchangeCounter, IAmounter<T> amounter) {
        Objects.requireNonNull(comparator);
        Objects.requireNonNull(exchangeCounter);
        Objects.requireNonNull(amounter);
        this.comparator = comparator;
        this.exchangeCounter = exchangeCounter;
        this.amounter = amounter;
    }

    int getMaxAmount(T[] arr) {
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

    int getMaxPlace(int value) {
        int place = 1;
        for (int i = 1; value > 0; i *= 10) {
            place++;
            value /= i;
        }
        return place;
    }

    @Override
    public T[] sort(T[] arr) {
        Objects.requireNonNull(arr);
        if (arr.length == 0) {
            return arr;
        }
        int maxAmount = getMaxAmount(arr);

        SingleArray<T> initial = new SingleArray<>(arr);
        SingleArray<T> first = initial;
        SingleArray<T> second = new SingleArray<>(arr.length);
        int maxPlace = getMaxPlace(maxAmount);
        int divider = 1;
        int amount;
        int index;
        T el;
        for (int i = 0; i <= maxPlace; i++) {
            int[] counter = new int[10];
            for (int j = 0; j < arr.length; j++) {
                el = first.get(j);
                amount = amounter.getAmount(el);
                index = (amount / divider) % 10;
                counter[index]++;
            }
            for (int j = 1; j < counter.length; j++) {
                counter[j] += counter[j - 1];
            }
            for (int j = arr.length - 1; j >= 0; j--) {
                el = first.get(j);
                amount = amounter.getAmount(el);
                index = (amount / divider) % 10;
                counter[index]--;
                second.set(el, counter[index]);
            }
            SingleArray<T> tmp = first;
            first = second;
            second = tmp;
            divider *= 10;
        }
        if (initial != second) {
            for (int i = 0; i < arr.length; i++) {
                arr[i] = second.get(i);
            }
        }
        return arr;
    }
}
