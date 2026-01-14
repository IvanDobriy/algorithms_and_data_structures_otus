package ru.otus.danilchenko.algorithms.test;

import ru.otus.danilchenko.algorithms.metrics.CompareWithMetic;
import ru.otus.danilchenko.algorithms.metrics.ExchangeMetrics;
import ru.otus.danilchenko.algorithms.metrics.Metric;
import ru.otus.danilchenko.algorithms.report.SimpleSortingReport;
import ru.otus.danilchenko.algorithms.report.SortingReportData;
import ru.otus.danilchenko.algorithms.sort.ISort;

import java.util.Arrays;
import java.util.Objects;

public class TreeTestWorkFlow {
    private final Test.TestRunnerParameters parameters;
    private final int size;
    private final Integer[] arr;
    private final Integer[] expected;
    private final ISort<Integer> sort;
    private final String name;
    private final Metric metric;
    private final SimpleSortingReport report;

    public TreeTestWorkFlow(
            String name,
            Test.TestRunnerParameters parameters,
            Metric metric,
            ISort<Integer> sort,
            SimpleSortingReport report
    ) {
        Objects.requireNonNull(name);
        Objects.requireNonNull(parameters);
        Objects.requireNonNull(sort);
        Objects.requireNonNull(metric);
        Objects.requireNonNull(report);

        this.metric = metric;
        this.name = name;
        this.sort = sort;
        this.parameters = parameters;
        this.report = report;

        size = Integer.parseInt(parameters.getInputData()[0]);
        arr = Arrays.stream(parameters.getInputData()[1].split(" ")).map(Integer::parseInt).toArray(Integer[]::new);
        expected = Arrays.stream(parameters.getExpectedData()[0].split(" ")).map(Integer::parseInt).toArray(Integer[]::new);
    }

    private void executeTest() {
        final var result = sort.sort(arr);
        if (!Arrays.deepEquals(expected, result)) {
            parameters.getOut().println(String.format("Test err"));
            return;
        }
        parameters.getOut().println("Test ok");

    }

    private void prepareReport() {
        final var metricResult = metric.getMetrics();
        final var compareMetrics = metricResult.getOrDefault(CompareWithMetic.TAG, 0L);
        final var exchangeMetrics = metricResult.getOrDefault(ExchangeMetrics.TAG, 0L);
        report.addReportData(name, parameters.getCasePath().toString(),
                new SortingReportData(size, compareMetrics, exchangeMetrics));
    }

    public void run() {
        executeTest();
        prepareReport();
    }
}
