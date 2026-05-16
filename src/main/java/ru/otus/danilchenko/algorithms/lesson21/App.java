package ru.otus.danilchenko.algorithms.lesson21;

import ru.otus.danilchenko.algorithms.test.Test;

import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class App {
    private void peasTest(Test.TestRunnerParameters parameters) {
        parameters.getOut().println("Test ok" + Arrays.toString(parameters.getInputData()) + " out: " + Arrays.toString(parameters.getExpectedData()));
    }

    private void run(String[] args) {
        final String defaultCaseName = "1";
        final var tests = List.of(new Test(
                "peas",
                defaultCaseName,
                Paths.get("./test_cases/lesson21/peas"),
                0, Integer.MAX_VALUE,
                this::peasTest
        ));

        for (var test : tests) {
            test.run();
        }
    }

    public static void main(String[] args) {
        final App app = new App();
        app.run(args);
    }
}
