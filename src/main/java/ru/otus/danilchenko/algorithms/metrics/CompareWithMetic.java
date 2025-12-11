package ru.otus.danilchenko.algorithms.metrics;

import ru.otus.danilchenko.algorithms.sort.IComparator;

import java.util.Objects;

public class CompareWithMetic<T> implements IComparator<T> {
    public final static String TAG = "comparing";
    private final IComparator<T> comparator;
    private final IMetriс metriс;

    public CompareWithMetic(IComparator<T> comparator, IMetriс metriс) {
        Objects.requireNonNull(comparator);
        Objects.requireNonNull(metriс);
        this.comparator = comparator;
        this.metriс = metriс;

    }

    @Override
    public int compare(T el1, T el2) {
        metriс.consider(TAG, 1);
        return comparator.compare(el1, el2);
    }
}
