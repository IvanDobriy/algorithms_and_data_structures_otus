package ru.otus.danilchenko.algorithms.lesson6;

import ru.otus.danilchenko.algorithms.test.Test;

import java.io.PrintStream;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

public class App {
    private final Logger logger = Logger.getLogger(this.getClass().getName());

    private void bubbleSortTest(String[] inputData, String[] expectedData, PrintStream out) {
        Integer[] arr = Arrays.stream(inputData[1].split(" ")).map(Integer::parseInt).toArray(Integer[]::new);
        Integer[] expected = Arrays.stream(expectedData[0].split(" ")).map(Integer::parseInt).toArray(Integer[]::new);

        final var metric = new Metric("bubble sort");
        final var utils = new Utils();
        final var comparator = new CompareWithMetic<>(utils::compare, metric);
        final var swapper = new SwapWithMetrics<Integer>(utils::swap, metric);
        final var bubbleSort = new BubbleSort<Integer>(comparator, swapper);

        final var result = bubbleSort.sort(arr);

        if (!Arrays.deepEquals(expected, result)) {
            out.println(String.format("Test err"));
            return;
        }
        out.println("Test ok");
    }


    private void run(String[] args) {
        final var tests = List.of(
                new Test("Bubble sort",
                        Paths.get("./test_cases/lesson6/sorting-tests/0.random"),
                        0, 5,
                        this::bubbleSortTest
                )
        );
        for(var test: tests) {
            test.run();
        }
    }

    public static void main(String[] args) {
        final App app = new App();
        app.run(args);
    }
}
