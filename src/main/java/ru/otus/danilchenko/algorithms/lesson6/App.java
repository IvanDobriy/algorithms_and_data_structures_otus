package ru.otus.danilchenko.algorithms.lesson6;

import java.util.Arrays;
import java.util.logging.Logger;

public class App {
    private final Logger logger = Logger.getLogger(this.getClass().getName());

    private void run(String[] args) {
        final var metric = new Metric("bubble sort");
        final var utils = new Utils();
        final var comparator = new CompareWithMetic<>(utils::compare, metric);
        final var swapper = new SwapWithMetrics<Integer>(utils::swap, metric);
        final var bubbleSort = new BubbleSort<Integer>(comparator, swapper);
        final Integer[] arr = {3, 2, 1, 0};
        final var result = bubbleSort.sort(arr);
        logger.info(String.format("%s", Arrays.toString(result)));
    }

    public static void main(String[] args) {
        final App app = new App();
        app.run(args);
    }
}
