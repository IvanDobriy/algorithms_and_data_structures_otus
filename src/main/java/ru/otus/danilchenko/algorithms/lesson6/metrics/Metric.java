package ru.otus.danilchenko.algorithms.lesson6.metrics;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Metric implements IMetriс {
    private final String name;
    private final Map<String, Long> metrics;

    public Metric(String name) {
        Objects.requireNonNull(name);
        this.name = name;
        this.metrics = new HashMap<>();
    }

    @Override
    public void consider(String tag, int count) {
        Long metricValue = metrics.getOrDefault(tag, 0L);
        metricValue += count;
        metrics.put(tag, metricValue);
    }

    public Map<String, Long> getMetrics() {
        return Map.copyOf(metrics);
    }

    public String getName() {
        return name;
    }
}
