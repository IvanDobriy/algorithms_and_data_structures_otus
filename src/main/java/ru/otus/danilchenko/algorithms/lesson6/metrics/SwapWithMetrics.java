package ru.otus.danilchenko.algorithms.lesson6.metrics;

import ru.otus.danilchenko.algorithms.lesson6.ISwap;

import java.util.Objects;

public class SwapWithMetrics<T> implements ISwap<T> {
    public final static String TAG = ExchangeMetrics.TAG;
    private final ISwap<T> swapper;
    private final IMetriс metriс;

    public SwapWithMetrics(ISwap<T> swapper, IMetriс metriс) {
        Objects.requireNonNull(swapper);
        Objects.requireNonNull(metriс);
        this.swapper = swapper;
        this.metriс = metriс;
    }

    @Override
    public void swap(T[] arr, int pos1, int pos2) {
        metriс.consider(TAG, 3);
        swapper.swap(arr, pos1, pos2);
    }
}

