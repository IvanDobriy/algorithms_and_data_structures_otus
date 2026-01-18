package ru.otus.danilchenko.algorithms.lesson10;

import ru.otus.danilchenko.algorithms.lesson8.QuickSort;
import ru.otus.danilchenko.algorithms.metrics.CompareWithMetic;
import ru.otus.danilchenko.algorithms.metrics.ExchangeMetrics;
import ru.otus.danilchenko.algorithms.metrics.Metric;
import ru.otus.danilchenko.algorithms.metrics.SwapWithMetrics;
import ru.otus.danilchenko.algorithms.report.SimpleSortingReport;
import ru.otus.danilchenko.algorithms.sort.Utils;
import ru.otus.danilchenko.algorithms.test.SortingTestWorkFlow;
import ru.otus.danilchenko.algorithms.test.Test;
import ru.otus.danilchenko.algorithms.test.TreeTestWorkFlow;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;

public class App implements AutoCloseable {

    private final SimpleSortingReport report = new SimpleSortingReport(Path.of("./reports/simpleReport.xls"));
    private final static Path RANDOM_TESTS = Paths.get("./test_cases/lesson6/sorting-tests/0.random");
//    private final static Path DIGITS_TESTS = Paths.get("./test_cases/lesson6/sorting-tests/1.digits");
    private final static Path SORTED_TESTS = Paths.get("./test_cases/lesson6/sorting-tests/2.sorted");
    private final static Path REVERS_TESTS = Paths.get("./test_cases/lesson6/sorting-tests/3.revers");
    private final static int MAX_CASES = 6;

    private void treeTest(Test.TestRunnerParameters parameters) {
        final var name = parameters.getTestName();
        final var insertMetric = new Metric(name);
        final var removeMetric = new Metric(name);
        final var searchMetric = new Metric(name);
        final var utils = new Utils();
        final var testFlow = new TreeTestWorkFlow(
                name,
                parameters,
                insertMetric,
                removeMetric,
                searchMetric,
                new BinarySearchTree<>(
                        new CompareWithMetic<>(utils::compare, insertMetric),
                        new ExchangeMetrics(insertMetric),
                        new CompareWithMetic<>(utils::compare, removeMetric),
                        new ExchangeMetrics(removeMetric),
                        new CompareWithMetic<>(utils::compare, searchMetric),
                        new ExchangeMetrics(searchMetric)
                ),
                report
        );
        testFlow.run();
    }


    private List<Test> prepareTests(List<AbstractMap.SimpleEntry<String, Test.TestRunner>> runners) {
        final List<Test> tests = new ArrayList<>();
        for (var runner : runners) {
            tests.addAll(List.of(
                    new Test(runner.getKey(),
                            "random",
                            RANDOM_TESTS,
                            0, MAX_CASES,
                            runner.getValue()
                    ),
//                    new Test(runner.getKey(),
//                            "digits",
//                            DIGITS_TESTS,
//                            0, MAX_CASES,
//                            runner.getValue()
//                    ),
                    new Test(runner.getKey(),
                            "sorted",
                            SORTED_TESTS,
                            0, MAX_CASES,
                            runner.getValue()
                    ),
                    new Test(runner.getKey(),
                            "revers",
                            REVERS_TESTS,
                            0, MAX_CASES,
                            runner.getValue()
                    )
            ));
        }
        return tests;
    }


    private void run(String[] args) {
        final var tests = prepareTests(List.of(
                new AbstractMap.SimpleEntry<>("1 tree test", this::treeTest)
        ));
        for (var test : tests) {
            test.run();
        }
    }

    @Override
    public void close() throws Exception {
        try {
            report.build();
        } finally {
            report.close();
        }
    }

    public static void main(String[] args) {
        try (var app = new App()) {
            app.run(args);
        } catch (Exception e) {
            throw new RuntimeException((e));
        }
    }
}
