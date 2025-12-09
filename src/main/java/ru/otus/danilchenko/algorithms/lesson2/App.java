package ru.otus.danilchenko.algorithms.lesson2;

import ru.otus.danilchenko.algorithms.test.Test;

import java.io.PrintStream;
import java.nio.file.Paths;
import java.util.List;

public class App {
    private static void stringTest(Test.TestRunnerParameters parameters) {
        int length = Integer.parseInt(parameters.getExpectedData()[0]);
        String string = parameters.getInputData()[0];
        if (string.length() != length) {
            parameters.getOut().println(String.format("Failed test, expected: %d actualL %d", length, string.length()));
        } else {
            parameters.getOut().println("Test is ok");
        }
    }

    private static void ticketsTest(Test.TestRunnerParameters parameters) {
        int nValue = Integer.parseInt(parameters.getInputData()[0]);
        long expected = Long.parseLong(parameters.getExpectedData()[0]);
        long result = TicketsCounter.count(nValue);
        if (result != expected) {
            parameters.getOut().println(String.format("Failed test, expected: %d actualL %d", expected, result));
        } else {
            parameters.getOut().println(String.format("Test ok, expected: %d, result: %d", expected, result));
        }
    }

    public static void main(String[] args) {
        final var tests = List.of(new Test(
                        "Tickets", "",
                        Paths.get("./test_cases/lesson2/1.Tickets"),
                        0, Integer.MAX_VALUE,
                        App::ticketsTest
                ), new Test(
                        "Strings", "",
                        Paths.get("./test_cases/lesson2/0.String"),
                        0, Integer.MAX_VALUE,
                        App::stringTest
                )
        );
        for (var test : tests) {
            test.run();
        }
    }
}
