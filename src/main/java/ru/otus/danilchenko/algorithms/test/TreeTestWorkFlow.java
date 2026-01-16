package ru.otus.danilchenko.algorithms.test;

import ru.otus.danilchenko.algorithms.collections.ITree;
import ru.otus.danilchenko.algorithms.metrics.CompareWithMetic;
import ru.otus.danilchenko.algorithms.metrics.ExchangeMetrics;
import ru.otus.danilchenko.algorithms.metrics.Metric;
import ru.otus.danilchenko.algorithms.report.SimpleSortingReport;
import ru.otus.danilchenko.algorithms.report.SortingReportData;

import java.util.AbstractMap;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class TreeTestWorkFlow {
    private final Test.TestRunnerParameters parameters;
    private final int size;
    private final Integer[] arr;
    private final Integer[] expected;
    private final ITree<Integer> tree;
    private final String name;
    private final Metric insertMetric;
    private final Metric deleteMetric;
    private final Metric removeMetric;
    private final SimpleSortingReport report;

    public TreeTestWorkFlow(
            String name,
            Test.TestRunnerParameters parameters,
            Metric insertMetric,
            Metric deleteMetric,
            Metric removeMetric,
            ITree<Integer> tree,
            SimpleSortingReport report
    ) {
        Objects.requireNonNull(name);
        Objects.requireNonNull(parameters);
        Objects.requireNonNull(tree);
        Objects.requireNonNull(insertMetric);
        Objects.requireNonNull(deleteMetric);
        Objects.requireNonNull(removeMetric);
        Objects.requireNonNull(report);

        this.insertMetric = insertMetric;
        this.deleteMetric = deleteMetric;
        this.removeMetric = removeMetric;

        this.name = name;
        this.tree = tree;
        this.parameters = parameters;
        this.report = report;

        size = Integer.parseInt(parameters.getInputData()[0]);
        arr = Arrays.stream(parameters.getInputData()[1].split(" ")).map(Integer::parseInt).toArray(Integer[]::new);
        expected = Arrays.stream(parameters.getExpectedData()[0].split(" ")).map(Integer::parseInt).toArray(Integer[]::new);
    }

    private void executeTest() {
        for (var el : arr) {
            tree.insert(el);
        }
        final var treeContent = tree.toArray();
        final var searchResult = tree.search(arr[0]);
        tree.remove(arr[0]);
        final var treeContentAfterRemove = tree.toArray();

        if (!searchResult) {
            parameters.getOut().println(String.format("Test err, element not found"));
        }

        for (var el : treeContentAfterRemove) {
            if (el.equals(arr[0])) {
                parameters.getOut().println(String.format("Test err, found removed element"));
                return;
            }
        }

        if (!Arrays.deepEquals(expected, treeContent)) {
            parameters.getOut().println(String.format("Test err"));
            return;
        }
        parameters.getOut().println("Test ok");

    }

    private void prepareReport() {
        final var metrics = List.of(
                new AbstractMap.SimpleEntry<>(" insert", insertMetric),
                new AbstractMap.SimpleEntry<>(" delete", deleteMetric),
                new AbstractMap.SimpleEntry<>(" remove", removeMetric)
        );

        for (var m : metrics) {
            var report = m.getValue().getMetrics();
            var compareMetrics = report.getOrDefault(CompareWithMetic.TAG, 0L);
            var exchangeMetrics = report.getOrDefault(ExchangeMetrics.TAG, 0L);

            this.report.addReportData(name, parameters.getCasePath().toString() + m.getKey(),
                    new SortingReportData(size, compareMetrics, exchangeMetrics));
        }
    }

    public void run() {
        executeTest();
        prepareReport();
    }
}
