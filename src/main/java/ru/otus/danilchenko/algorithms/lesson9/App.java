package ru.otus.danilchenko.algorithms.lesson9;

import ru.otus.danilchenko.algorithms.lesson3.Pow;
import ru.otus.danilchenko.algorithms.lesson8.QuickSort;
import ru.otus.danilchenko.algorithms.metrics.CompareWithMetic;
import ru.otus.danilchenko.algorithms.metrics.ExchangeMetrics;
import ru.otus.danilchenko.algorithms.metrics.Metric;
import ru.otus.danilchenko.algorithms.metrics.SwapWithMetrics;
import ru.otus.danilchenko.algorithms.report.SimpleSortingReport;
import ru.otus.danilchenko.algorithms.sort.Utils;
import ru.otus.danilchenko.algorithms.test.SortingTestWorkFlow;
import ru.otus.danilchenko.algorithms.test.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Exchanger;

public class App implements AutoCloseable {
    private final SimpleSortingReport report = new SimpleSortingReport(Path.of("./reports/simpleReport.xls"));
    private final static Path RANDOM_TESTS = Paths.get("./generated");
    private final static int MAX_CASES = 10;

    private void bucketSortTest(Test.TestRunnerParameters parameters) {
        final var name = parameters.getTestName();
        final var metric = new Metric(name);
        final var utils = new Utils();
        final IAmounter<Integer> amounter = (Integer value) -> {
            return value;
        };
        final var comparator = new CompareWithMetic<>(utils::compare, metric);
        final var exchangeMetrics = new ExchangeMetrics(metric);
        final var testFlow = new SortingTestWorkFlow(name, parameters, metric, new BucketSort<>(comparator, exchangeMetrics, amounter), report);
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
                    )
            ));
        }
        return tests;
    }

    private void run(String[] args) {
        final var tests = prepareTests(List.of(
                new AbstractMap.SimpleEntry<>("1 bucket sort", this::bucketSortTest)
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


    private static void generateRandom() {
        final RandomGenerator generator = new RandomGenerator();
        for (int i = 0; i < 9; i++) {
            generator.generate((int) Pow.bin(10, i),
                    Paths.get(String.format("./generated/test.%d.in", i)),
                    Paths.get(String.format("./generated/test.%d.out", i)));
        }
    }

    public static void main(String[] args) {
//        generateRandom();
        try (var app = new App()) {
            app.run(args);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
