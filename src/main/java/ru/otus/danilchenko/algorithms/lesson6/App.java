package ru.otus.danilchenko.algorithms.lesson6;

import ru.otus.danilchenko.algorithms.lesson6.metrics.CompareWithMetic;
import ru.otus.danilchenko.algorithms.lesson6.metrics.Metric;
import ru.otus.danilchenko.algorithms.lesson6.metrics.SwapWithMetrics;
import ru.otus.danilchenko.algorithms.lesson6.report.SimpleSortingReport;
import ru.otus.danilchenko.algorithms.lesson6.report.SortingReportData;
import ru.otus.danilchenko.algorithms.test.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class App implements AutoCloseable {
    private final SimpleSortingReport report = new SimpleSortingReport(Path.of("./reports/simpleReport.xls"));
    private final static Path RANDOM_TESTS = Paths.get("./test_cases/lesson6/sorting-tests/0.random");
    private final static Path DIGITS_TESTS = Paths.get("./test_cases/lesson6/sorting-tests/1.digits");
    private final static Path SORTED_TESTS = Paths.get("./test_cases/lesson6/sorting-tests/2.sorted");
    private final static Path REVERS_TESTS = Paths.get("./test_cases/lesson6/sorting-tests/3.revers");


    private class TestWorkFlow {
        private final Test.TestRunnerParameters parameters;
        private final int size;
        private final Integer[] arr;
        private final Integer[] expected;
        private final ISort<Integer> sort;
        private final String name;
        private final Metric metric;


        public TestWorkFlow(String name, Test.TestRunnerParameters parameters, Metric metric, ISort<Integer> sort) {
            Objects.requireNonNull(name);
            Objects.requireNonNull(parameters);
            Objects.requireNonNull(sort);
            Objects.requireNonNull(metric);

            this.metric = metric;
            this.name = name;
            this.sort = sort;
            this.parameters = parameters;
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
            final var compareMetrics = metricResult.getOrDefault(CompareWithMetic.TAG, 0);
            final var exchangeMetrics = metricResult.getOrDefault(SwapWithMetrics.TAG, 0);
            report.addReportData(name, parameters.getCasePath().toString(),
                    new SortingReportData(size, compareMetrics, exchangeMetrics));
        }

        public void run() {
            executeTest();
            prepareReport();
        }
    }

    private void bubbleSortTest(Test.TestRunnerParameters parameters) {
        final var name = "Bubble sort";
        final var metric = new Metric(name);
        final var utils = new Utils();
        final var comparator = new CompareWithMetic<>(utils::compare, metric);
        final var swapper = new SwapWithMetrics<Integer>(utils::swap, metric);
        final var testFlow = new TestWorkFlow(name, parameters, metric, new BubbleSort<>(comparator, swapper));
        testFlow.run();
    }


    private void insertionSortTest(Test.TestRunnerParameters parameters) {
        final var name = "Insertion sort";
        final var metric = new Metric(name);
        final var utils = new Utils();
        final var comparator = new CompareWithMetic<>(utils::compare, metric);
        final var swapper = new SwapWithMetrics<Integer>(utils::swap, metric);
        final var testFlow = new TestWorkFlow(name, parameters, metric, new InsertionSort<>(comparator, swapper));
        testFlow.run();
    }

    private void shellSortTest(Test.TestRunnerParameters parameters) {
        final var name = "Shell sort";
        final var metric = new Metric(name);
        final var utils = new Utils();
        final var comparator = new CompareWithMetic<>(utils::compare, metric);
        final var swapper = new SwapWithMetrics<Integer>(utils::swap, metric);
        final var testFlow = new TestWorkFlow(name, parameters, metric, new ShellSort<>(comparator, swapper));
        testFlow.run();
    }

    private void run(String[] args) {
        final var tests = List.of(
                new Test("Bubble sort random",
                        RANDOM_TESTS,
                        0, 5,
                        this::bubbleSortTest
                ),
                new Test("Bubble sort digits",
                        DIGITS_TESTS,
                        0, 5,
                        this::bubbleSortTest
                ),
                new Test("Bubble sort sorted",
                        SORTED_TESTS,
                        0, 5,
                        this::bubbleSortTest
                ),
                new Test("Bubble sort revers",
                        REVERS_TESTS,
                        0, 5,
                        this::bubbleSortTest
                ),
                new Test("Insertion sort random",
                        RANDOM_TESTS,
                        0, 5,
                        this::insertionSortTest
                ),
                new Test("Insertion sort digits",
                        DIGITS_TESTS,
                        0, 5,
                        this::insertionSortTest
                ),
                new Test("Insertion sort sorted",
                        SORTED_TESTS,
                        0, 5,
                        this::insertionSortTest
                ),
                new Test("Insertion sort revers",
                        REVERS_TESTS,
                        0, 5,
                        this::insertionSortTest
                ),
                new Test("Shell sort random",
                        RANDOM_TESTS,
                        0, 5,
                        this::shellSortTest
                ),
                new Test("Shell sort digits",
                        DIGITS_TESTS,
                        0, 5,
                        this::shellSortTest
                ),
                new Test("Shell sort sorted",
                        SORTED_TESTS,
                        0, 5,
                        this::shellSortTest
                ),
                new Test("Shell sort revers",
                        REVERS_TESTS,
                        0, 5,
                        this::shellSortTest
                )
        );
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
        try (final App app = new App()) {
            app.run(args);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
