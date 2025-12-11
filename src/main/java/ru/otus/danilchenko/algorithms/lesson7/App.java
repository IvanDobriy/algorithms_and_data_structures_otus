package ru.otus.danilchenko.algorithms.lesson7;

import ru.otus.danilchenko.algorithms.report.SimpleSortingReport;

import java.nio.file.Path;
import java.nio.file.Paths;

public class App implements AutoCloseable {
    private final SimpleSortingReport report = new SimpleSortingReport(Path.of("./reports/simpleReport.xls"));
    private final static Path RANDOM_TESTS = Paths.get("./test_cases/lesson6/sorting-tests/0.random");
    private final static Path DIGITS_TESTS = Paths.get("./test_cases/lesson6/sorting-tests/1.digits");
    private final static Path SORTED_TESTS = Paths.get("./test_cases/lesson6/sorting-tests/2.sorted");
    private final static Path REVERS_TESTS = Paths.get("./test_cases/lesson6/sorting-tests/3.revers");
    private final static int MAX_CASES = 5;

    @Override
    public void close() throws Exception {

    }
}
