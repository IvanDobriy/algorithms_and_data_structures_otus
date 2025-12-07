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
import java.util.logging.Logger;

public class App implements AutoCloseable {
    private final Logger logger = Logger.getLogger(this.getClass().getName());
    private final SimpleSortingReport report = new SimpleSortingReport(Path.of("./reports/simpleReport.xls"));
    private final static Path RANDOM_TESTS = Paths.get("./test_cases/lesson6/sorting-tests/0.random");
    private final static Path DIGITS_TESTS = Paths.get("./test_cases/lesson6/sorting-tests/1.digits");
    private final static Path SORTED_TESTS = Paths.get("./test_cases/lesson6/sorting-tests/2.sorted");
    private final static Path REVERS_TESTS = Paths.get("./test_cases/lesson6/sorting-tests/3.revers");


    private void bubbleSortTest(Test.TestRunnerParameters parameters) {
        int size = Integer.parseInt(parameters.getInputData()[0]);
        Integer[] arr = Arrays.stream(parameters.getInputData()[1].split(" ")).map(Integer::parseInt).toArray(Integer[]::new);
        Integer[] expected = Arrays.stream(parameters.getExpectedData()[0].split(" ")).map(Integer::parseInt).toArray(Integer[]::new);

        final var metric = new Metric("Bubble sort");
        final var utils = new Utils();
        final var comparator = new CompareWithMetic<>(utils::compare, metric);
        final var swapper = new SwapWithMetrics<Integer>(utils::swap, metric);
        final var bubbleSort = new BubbleSort<Integer>(comparator, swapper);

        final var result = bubbleSort.sort(arr);

        final var metricResult = metric.getMetrics();
        final var compareMetrics = metricResult.get(CompareWithMetic.TAG);
        final var exchangeMetrics = metricResult.get(SwapWithMetrics.TAG);
        report.addReportData("bubbleSort", parameters.getCasePath().toString(),
                new SortingReportData(size, compareMetrics == null ? 0 : compareMetrics,
                        exchangeMetrics == null ? 0 : exchangeMetrics));
        if (!Arrays.deepEquals(expected, result)) {
            parameters.getOut().println(String.format("Test err"));
            return;
        }
        parameters.getOut().println("Test ok");
    }


    private void insertionSortTest(Test.TestRunnerParameters parameters) {
        int size = Integer.parseInt(parameters.getInputData()[0]);
        Integer[] arr = Arrays.stream(parameters.getInputData()[1].split(" ")).map(Integer::parseInt).toArray(Integer[]::new);
        Integer[] expected = Arrays.stream(parameters.getExpectedData()[0].split(" ")).map(Integer::parseInt).toArray(Integer[]::new);

        final var metric = new Metric("Insertion sort");
        final var utils = new Utils();
        final var comparator = new CompareWithMetic<>(utils::compare, metric);
        final var swapper = new SwapWithMetrics<Integer>(utils::swap, metric);
        final var insertionSort = new InsertionSort<Integer>(comparator, swapper);

        final var result = insertionSort.sort(arr);

        final var metricResult = metric.getMetrics();
        final var compareMetrics = metricResult.get(CompareWithMetic.TAG);
        final var exchangeMetrics = metricResult.get(SwapWithMetrics.TAG);
        report.addReportData("insertionSort", parameters.getCasePath().toString(),
                new SortingReportData(size, compareMetrics == null ? 0 : compareMetrics,
                        exchangeMetrics == null ? 0 : exchangeMetrics));
        if (!Arrays.deepEquals(expected, result)) {
            parameters.getOut().println(String.format("Test err"));
            return;
        }
        parameters.getOut().println("Test ok");
    }

    private void shellSortTest(Test.TestRunnerParameters parameters) {
        int size = Integer.parseInt(parameters.getInputData()[0]);
        Integer[] arr = Arrays.stream(parameters.getInputData()[1].split(" ")).map(Integer::parseInt).toArray(Integer[]::new);
        Integer[] expected = Arrays.stream(parameters.getExpectedData()[0].split(" ")).map(Integer::parseInt).toArray(Integer[]::new);

        final var metric = new Metric("Shell sort");
        final var utils = new Utils();
        final var comparator = new CompareWithMetic<>(utils::compare, metric);
        final var swapper = new SwapWithMetrics<Integer>(utils::swap, metric);
        final var shellSort = new ShellSort<Integer>(comparator, swapper);

        final var result = shellSort.sort(arr);

        final var metricResult = metric.getMetrics();
        final var compareMetrics = metricResult.get(CompareWithMetic.TAG);
        final var exchangeMetrics = metricResult.get(SwapWithMetrics.TAG);
        report.addReportData("shellSort", parameters.getCasePath().toString(),
                new SortingReportData(size, compareMetrics == null ? 0 : compareMetrics,
                        exchangeMetrics == null ? 0 : exchangeMetrics));
        if (!Arrays.deepEquals(expected, result)) {
            parameters.getOut().println(String.format("Test err"));
            return;
        }
        parameters.getOut().println("Test ok");
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
