package ru.otus.danilchenko.algorithms.lesson3;

import ru.otus.danilchenko.algorithms.test.DoubleComparator;
import ru.otus.danilchenko.algorithms.test.Test;

import java.io.PrintStream;
import java.nio.file.Paths;
import java.util.List;

public class App {
    private static void simpleIterationPowTest(String[] inputData, String[] expectedData, PrintStream out) {
        double digit = Double.parseDouble(inputData[0]);
        long degree = Long.parseLong(inputData[1]);
        double expected = Double.parseDouble(expectedData[0]);
        double result = Pow.simpleIteration(digit, degree);
        if (!DoubleComparator.equals(expected, result)) {
            out.println(String.format("Failed test, expected: %s, result: %s", expected, result));
        } else {
            out.println(String.format("Test ok, expected: %s, result: %s", expected, result));
        }
    }

    private static void iterationPowTest(String[] inputData, String[] expectedData, PrintStream out) {
        double digit = Double.parseDouble(inputData[0]);
        long degree = Long.parseLong(inputData[1]);
        double expected = Double.parseDouble(expectedData[0]);
        double result = Pow.iteration(digit, degree);
        if (!DoubleComparator.equals(expected, result)) {
            out.println(String.format("Failed test, expected: %s, result: %s", expected, result));
        } else {
            out.println(String.format("Test ok, expected: %s, result: %s", expected, result));
        }
    }

    private static void twoPowTest(String[] inputData, String[] expectedData, PrintStream out) {
        double digit = Double.parseDouble(inputData[0]);
        long degree = Long.parseLong(inputData[1]);
        double expected = Double.parseDouble(expectedData[0]);
        double result = Pow.two(digit, degree);
        if (!DoubleComparator.equals(expected, result)) {
            out.println(String.format("Failed test, expected: %s, result: %s", expected, result));
        } else {
            out.println(String.format("Test ok, expected: %s, result: %s", expected, result));
        }
    }

    private static void binPowTest(String[] inputData, String[] expectedData, PrintStream out) {
        double digit = Double.parseDouble(inputData[0]);
        long degree = Long.parseLong(inputData[1]);
        double expected = Double.parseDouble(expectedData[0]);
        double result = Pow.bin(digit, degree);
        if (!DoubleComparator.equals(expected, result)) {
            out.println(String.format("Failed test, expected: %s, result: %s", expected, result));
        } else {
            out.println(String.format("Test ok, expected: %s, result: %s", expected, result));
        }
    }

    public static void main(String[] args) {
        final var tests = List.of(new Test(
                        "Simple iteration pow test",
                        Paths.get("./test_cases/lesson3/3.Power"),
                        0, 9,
                        App::simpleIterationPowTest
                )
                , new Test(
                        "Iteration pow test",
                        Paths.get("./test_cases/lesson3/3.Power"),
                        0, 9,
                        App::iterationPowTest
                )
                , new Test(
                        "Two pow test",
                        Paths.get("./test_cases/lesson3/3.Power"),
                        0, Integer.MAX_VALUE,
                        App::twoPowTest
                ),
                new Test(
                        "Bin pow test",
                        Paths.get("./test_cases/lesson3/3.Power"),
                        0, Integer.MAX_VALUE,
                        App::binPowTest
                )
        );
        for (var test : tests) {
            test.run();
        }
    }
}
