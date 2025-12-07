package ru.otus.danilchenko.algorithms.lesson6;

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
        report.addReportData("bubbleSort", parameters.getCasePath().toString(),
                new SortingReportData(size, metricResult.get(CompareWithMetic.TAG), metricResult.get(SwapWithMetrics.TAG)));
        if (!Arrays.deepEquals(expected, result)) {
            parameters.getOut().println(String.format("Test err"));
            return;
        }
        parameters.getOut().println("Test ok");
    }

    private void run(String[] args) {
        final var tests = List.of(
                new Test("Bubble sort random",
                        Paths.get("./test_cases/lesson6/sorting-tests/0.random"),
                        0, 5,
                        this::bubbleSortTest
                ),
                new Test("Bubble sort digits",
                        Paths.get("./test_cases/lesson6/sorting-tests/1.digits"),
                        0, 5,
                        this::bubbleSortTest
                )
        );
        for (var test : tests) {
            test.run();
        }
    }

    @Override
    public void close() throws Exception {
        report.build();
        report.close();
    }

    public static void main(String[] args) {
        try (final App app = new App()) {
            app.run(args);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
