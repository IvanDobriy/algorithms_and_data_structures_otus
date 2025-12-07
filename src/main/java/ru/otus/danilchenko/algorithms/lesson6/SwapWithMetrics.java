package ru.otus.danilchenko.algorithms.lesson6;

import java.util.Objects;

public class SwapWithMetrics<T> implements ISwap<T> {
    final static String TAG = "exchange";
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

