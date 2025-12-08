package ru.otus.danilchenko.algorithms.lesson6;

import ru.otus.danilchenko.algorithms.lesson6.metrics.CompareWithMetic;
import ru.otus.danilchenko.algorithms.lesson6.metrics.Metric;
import ru.otus.danilchenko.algorithms.lesson6.metrics.SwapWithMetrics;
import ru.otus.danilchenko.algorithms.lesson6.report.SimpleSortingReport;
import ru.otus.danilchenko.algorithms.lesson6.report.SortingReportData;
import ru.otus.danilchenko.algorithms.test.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class App implements AutoCloseable {
    private final SimpleSortingReport report = new SimpleSortingReport(Path.of("./reports/simpleReport.xls"));
    private final static Path RANDOM_TESTS = Paths.get("./test_cases/lesson6/sorting-tests/0.random");
    private final static Path DIGITS_TESTS = Paths.get("./test_cases/lesson6/sorting-tests/1.digits");
    private final static Path SORTED_TESTS = Paths.get("./test_cases/lesson6/sorting-tests/2.sorted");
    private final static Path REVERS_TESTS = Paths.get("./test_cases/lesson6/sorting-tests/3.revers");
    private final static int MAX_CASES = 5;


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

    private void optimizedBubbleSortTest(Test.TestRunnerParameters parameters) {
        final var name = "Optimized bubble sort";
        final var metric = new Metric(name);
        final var utils = new Utils();
        final var comparator = new CompareWithMetic<>(utils::compare, metric);
        final var swapper = new SwapWithMetrics<Integer>(utils::swap, metric);
        final var testFlow = new TestWorkFlow(name, parameters, metric, new OptimizedBubbleSort<>(comparator, swapper));
        testFlow.run();
    }

    private void shiftedInsertionSortTest(Test.TestRunnerParameters parameters) {
        final var name = "Shifted insertion sort";
        final var metric = new Metric(name);
        final var utils = new Utils();
        final var comparator = new CompareWithMetic<>(utils::compare, metric);
        final var swapper = new SwapWithMetrics<Integer>(utils::swap, metric);
        final var testFlow = new TestWorkFlow(name, parameters, metric, new ShiftedInsertionSort<>(comparator, swapper));
        testFlow.run();
    }

    private List<Test> prepareTests(List<AbstractMap.SimpleEntry<String, Test.TestRunner>> runners) {
        final List<Test> tests = new ArrayList<>();
        for (var runner : runners) {
            tests.addAll(List.of(
                    new Test(runner.getKey() + " random",
                            RANDOM_TESTS,
                            0, MAX_CASES,
                            runner.getValue()
                    ),
                    new Test(runner.getKey() + " digits",
                            DIGITS_TESTS,
                            0, MAX_CASES,
                            runner.getValue()
                    ),
                    new Test(runner.getKey() + " sorted",
                            SORTED_TESTS,
                            0, MAX_CASES,
                            runner.getValue()
                    ),
                    new Test(runner.getKey() + " revers",
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
                new AbstractMap.SimpleEntry<>("Bubble sort", this::bubbleSortTest),
                new AbstractMap.SimpleEntry<>("Insertion sort", this::insertionSortTest),
                new AbstractMap.SimpleEntry<>("Shell sort", this::shellSortTest),
                new AbstractMap.SimpleEntry<>("Optimized bubble sort", this::optimizedBubbleSortTest),
                new AbstractMap.SimpleEntry<>("Shifted insertion sort", this::shiftedInsertionSortTest)

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
        try (final App app = new App()) {
            app.run(args);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
