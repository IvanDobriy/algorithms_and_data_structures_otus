package ru.otus.danilchenko.algorithms.lesson6.metrics;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Metric implements IMetriс {
    private final String name;
    private final Map<String, Integer> metrics;

    public Metric(String name) {
        Objects.requireNonNull(name);
        this.name = name;
        this.metrics = new HashMap<>();
    }

    @Override
    public void consider(String tag, int count) {
        Integer metricValue = metrics.getOrDefault(tag, 0);
        metricValue += count;
        metrics.put(tag, metricValue);
    }

    public Map<String, Integer> getMetrics() {
        return Map.copyOf(metrics);
    }

    public String getName() {
        return name;
    }
}
