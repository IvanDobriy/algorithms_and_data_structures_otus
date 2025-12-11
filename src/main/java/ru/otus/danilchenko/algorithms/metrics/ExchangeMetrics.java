package ru.otus.danilchenko.algorithms.metrics;

import java.util.Objects;

public class ExchangeMetrics implements IExchangeCounter {
    public final static String TAG = "exchages";
    private final IMetriс metriс;

    public ExchangeMetrics(IMetriс metriс) {
        Objects.requireNonNull(metriс);
        this.metriс = metriс;
    }

    @Override
    public void count(int value) {
        metriс.consider(TAG, value);
    }
}
