package ru.otus.danilchenko.algorithms.test;

import ru.otus.danilchenko.algorithms.collections.ITree;
import ru.otus.danilchenko.algorithms.metrics.Metric;
import ru.otus.danilchenko.algorithms.report.SimpleSortingReport;
import ru.otus.danilchenko.algorithms.report.TreeReport;
import ru.otus.danilchenko.algorithms.report.TreeReportData;

import java.util.Arrays;
import java.util.Objects;

public class TreeTestWorkFlow {
    private final Test.TestRunnerParameters parameters;
    private final int size;
    private final Integer[] arr;
    private final Integer[] expected;
    private final ITree<Integer> tree;
    private final String name;
    private final TreeReport report;
    private Long insertTime = 0L;
    private Long removeTime = 0L;
    private Long searchTime = 0L;

    public TreeTestWorkFlow(
            String name,
            Test.TestRunnerParameters parameters,
            ITree<Integer> tree,
            TreeReport report
    ) {
        Objects.requireNonNull(name);
        Objects.requireNonNull(parameters);
        Objects.requireNonNull(tree);
        Objects.requireNonNull(report);


        this.name = name;
        this.tree = tree;
        this.parameters = parameters;
        this.report = report;

        size = Integer.parseInt(parameters.getInputData()[0]);
        arr = Arrays.stream(parameters.getInputData()[1].split(" ")).map(Integer::parseInt).toArray(Integer[]::new);
        expected = Arrays.stream(parameters.getExpectedData()[0].split(" ")).map(Integer::parseInt).toArray(Integer[]::new);
    }

    private void fill() {
        var before = System.currentTimeMillis();
        for (var el : arr) {
            tree.insert(el);
        }
        insertTime = System.currentTimeMillis() - before;
    }

    private void remove(int[] toRemove) {
        var before = System.currentTimeMillis();
        for (int index : toRemove) {
            tree.remove(arr[index]);
        }
        removeTime = System.currentTimeMillis() - before;
    }

    private boolean[] search(int[] toSearch, Boolean useMetrics) {
        var before = System.currentTimeMillis();
        final boolean[] searchResult = new boolean[toSearch.length];
        for (int i = 0; i < toSearch.length; i++) {
            var value = arr[toSearch[i]];
            searchResult[i] = tree.search(value);
        }
        if (useMetrics) {
            searchTime = System.currentTimeMillis() - before;
        }
        return searchResult;
    }


    int[] getArrIndexes() {
        final int[] result = new int[arr.length / 10];
        int nextIndex = 0;
        for (int i = 0; i < result.length; i++) {
            result[i] = arr[nextIndex];
            nextIndex += result.length - 1;
            if(nextIndex >= arr.length){
                nextIndex = arr.length -1;
            }
        }
        return result;
    }

    private void executeTest() {
        int[] indexes = getArrIndexes();
        fill();
        boolean[] result = search(indexes, true);
        for (boolean el : result) {
            if (!el) {
                parameters.getOut().println("Test err");
                return;
            }
        }
        remove(indexes);
        result = search(indexes, false);
        for (boolean el : result) {
            if (!el) {
                parameters.getOut().println("Test err");
                return;
            }
        }
        parameters.getOut().println("Test ok");
    }

    private void prepareReport() {
        this.report.addReportData(name, parameters.getCasePath().toString(), new TreeReportData(arr.length, insertTime, removeTime, searchTime));
    }

    public void run() {
        executeTest();
        prepareReport();
    }
}
