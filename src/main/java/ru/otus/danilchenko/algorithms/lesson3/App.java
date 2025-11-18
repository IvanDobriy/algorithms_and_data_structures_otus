package ru.otus.danilchenko.algorithms.lesson3;

import ru.otus.danilchenko.algorithms.test.DoubleComparator;
import ru.otus.danilchenko.algorithms.test.Test;

import java.io.PrintStream;
import java.math.BigInteger;
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


    private static void simpleFibonacciRecursionTest(String[] inputData, String[] expectedData, PrintStream out) {
        BigInteger period = new BigInteger(inputData[0]);
        BigInteger expected = new BigInteger(expectedData[0]);
        BigInteger result = Fibonacci.simpleRecursion(period);
        if (!expected.equals(result)) {
            out.println(String.format("Failed test, expected: %s, result: %s", expected, result));
        } else {
            out.println(String.format("Test ok, expected: %s, result: %s", expected, result));
        }
    }


    private static void iterationFibonacciRecursionTest(String[] inputData, String[] expectedData, PrintStream out) {
        BigInteger period = new BigInteger(inputData[0]);
        BigInteger expected = new BigInteger(expectedData[0]);
        BigInteger result = Fibonacci.iteration(period);
        if (!expected.equals(result)) {
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
                ),
                new Test(
                        "Fibonacci simple recursion test",
                        Paths.get("./test_cases/lesson3/4.Fibo"),
                        0, 7,
                        App::simpleFibonacciRecursionTest
                ),
                new Test(
                        "Fibonacci iteration test",
                        Paths.get("./test_cases/lesson3/4.Fibo"),
                        0, 8,
                        App::iterationFibonacciRecursionTest
                )
        );
        for (var test : tests) {
            test.run();
        }
    }
}
