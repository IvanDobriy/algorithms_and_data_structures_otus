package ru.otus.danilchenko.algorithms.lesson21;

import ru.otus.danilchenko.algorithms.lesson21.peas.Peas;
import ru.otus.danilchenko.algorithms.test.Test;

import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class App {
    private void peasTest(Test.TestRunnerParameters parameters) {
        final Peas peas = new Peas();
        String result = peas.calculate(parameters.getInputData()[0]);
        if(parameters.getExpectedData()[0].equals(result)){
            parameters.getOut().println("Test ok");
        }else {
            parameters.getOut().println("Test err, in: " + Arrays.toString(parameters.getInputData()) + " expected: " + Arrays.toString(parameters.getExpectedData()) + " result: " + result);
        }
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
