package ru.otus.danilchenko.algorithms.lesson2;

import java.io.PrintStream;
import java.nio.file.Paths;
import java.util.List;

public class App {
    private static void stringTest(String[] inputData, String[] expectedData, PrintStream out) {
        int length = Integer.parseInt(expectedData[0]);
        String string = inputData[0];
        if (string.length() != length) {
            out.println(String.format("Failed test, expected: %d actualL %d", length, string.length()));
        } else {
            out.println("Test is ok");
        }
    }

    private static void ticketsTest(String[] inputData, String[] expectedData, PrintStream out) {
        int nValue = Integer.parseInt(inputData[0]);
        long expected = Long.parseLong(expectedData[0]);
        long result = TicketsCounter.count(nValue);
        if (result != expected) {
            out.println(String.format("Failed test, expected: %d actualL %d", expected, result));
        } else {
            out.println(String.format("Test ok, expected: %d, result: %d", expected, result));
        }
    }

    public static void main(String[] args) {
        final var tests = List.of(new Test(
                        "Tickets",
                        Paths.get("./test_cases/lesson2/1.Tickets"),
                        0, Integer.MAX_VALUE,
                        App::ticketsTest
                ), new Test(
                        "Strings",
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
