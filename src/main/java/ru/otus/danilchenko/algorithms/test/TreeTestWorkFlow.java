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
    private final Metric metric;
    private final SimpleSortingReport report;

    public TreeTestWorkFlow(
            String name,
            Test.TestRunnerParameters parameters,
            Metric metric,
            ITree<Integer> tree,
            SimpleSortingReport report
    ) {
        Objects.requireNonNull(name);
        Objects.requireNonNull(parameters);
        Objects.requireNonNull(tree);
        Objects.requireNonNull(metric);
        Objects.requireNonNull(report);

        this.metric = metric;

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


        for (int i = 0; i < treeContentAfterRemove.size(); i++) {
            var el = treeContentAfterRemove.get(i);
            if (el.equals(arr[0])) {
                parameters.getOut().println(String.format("Test err, found removed element"));
                return;
            }
        }

        for (int i = 0; i < treeContent.size(); i++) {
            if (!expected[i].equals(treeContent.get(i))) {
                parameters.getOut().println(String.format("Test err"));
                return;
            }
        }
        parameters.getOut().println("Test ok");

    }

    private void prepareReport() {
        this.report.addReportData(name, parameters.getCasePath(),  new SortingReportData(size, compareMetrics, exchangeMetrics));
    }

    public void run() {
        executeTest();
        prepareReport();
    }
}
