package ru.otus.danilchenko.algorithms.lesson3;

import ru.otus.danilchenko.algorithms.test.DoubleComparator;
import ru.otus.danilchenko.algorithms.test.Test;

import java.io.PrintStream;
import java.math.BigInteger;
import java.nio.file.Paths;
import java.util.List;

public class App {
    private static void simpleIterationPowTest(Test.TestRunnerParameters parameters) {
        double digit = Double.parseDouble(parameters.getInputData()[0]);
        long degree = Long.parseLong(parameters.getInputData()[1]);
        double expected = Double.parseDouble(parameters.getExpectedData()[0]);
        double result = Pow.simpleIteration(digit, degree);
        if (!DoubleComparator.equals(expected, result)) {
            parameters.getOut().println(String.format("Failed test, expected: %s, result: %s", expected, result));
        } else {
            parameters.getOut().println(String.format("Test ok, expected: %s, result: %s", expected, result));
        }
    }

    private static void iterationPowTest(Test.TestRunnerParameters parameters) {
        double digit = Double.parseDouble(parameters.getInputData()[0]);
        long degree = Long.parseLong(parameters.getInputData()[1]);
        double expected = Double.parseDouble(parameters.getExpectedData()[0]);
        double result = Pow.iteration(digit, degree);
        if (!DoubleComparator.equals(expected, result)) {
            parameters.getOut().println(String.format("Failed test, expected: %s, result: %s", expected, result));
        } else {
            parameters.getOut().println(String.format("Test ok, expected: %s, result: %s", expected, result));
        }
    }

    private static void twoPowTest(Test.TestRunnerParameters parameters) {
        double digit = Double.parseDouble(parameters.getInputData()[0]);
        long degree = Long.parseLong(parameters.getInputData()[1]);
        double expected = Double.parseDouble(parameters.getExpectedData()[0]);
        double result = Pow.two(digit, degree);
        if (!DoubleComparator.equals(expected, result)) {
            parameters.getOut().println(String.format("Failed test, expected: %s, result: %s", expected, result));
        } else {
            parameters.getOut().println(String.format("Test ok, expected: %s, result: %s", expected, result));
        }
    }

    private static void binPowTest(Test.TestRunnerParameters parameters) {
        double digit = Double.parseDouble(parameters.getInputData()[0]);
        long degree = Long.parseLong(parameters.getInputData()[1]);
        double expected = Double.parseDouble(parameters.getExpectedData()[0]);
        double result = Pow.bin(digit, degree);
        if (!DoubleComparator.equals(expected, result)) {
            parameters.getOut().println(String.format("Failed test, expected: %s, result: %s", expected, result));
        } else {
            parameters.getOut().println(String.format("Test ok, expected: %s, result: %s", expected, result));
        }
    }


    private static void simpleFibonacciRecursionTest(Test.TestRunnerParameters parameters) {
        int period = Integer.parseInt(parameters.getInputData()[0]);
        BigInteger expected = new BigInteger(parameters.getExpectedData()[0]);
        BigInteger result = Fibonacci.simpleRecursion(period);
        if (!expected.equals(result)) {
            parameters.getOut().println(String.format("Failed test, expected: %s, result: %s", expected, result));
        } else {
            parameters.getOut().println(String.format("Test ok, expected: %s, result: %s", expected, result));
        }
    }


    private static void iterationFibonacciRecursionTest(Test.TestRunnerParameters parameters) {
        int period = Integer.parseInt(parameters.getInputData()[0]);
        BigInteger expected = new BigInteger(parameters.getExpectedData()[0]);
        BigInteger result = Fibonacci.iteration(period);
        if (!expected.equals(result)) {
            parameters.getOut().println(String.format("Failed test, expected: %s, result: %s", expected, result));
        } else {
            parameters.getOut().println("Test ok");
        }
    }


    private static void goldenRatioFibonacciRecursionTest(Test.TestRunnerParameters parameters) {
        int period = Integer.parseInt(parameters.getInputData()[0]);
        BigInteger expected = new BigInteger(parameters.getExpectedData()[0]);
        BigInteger result = Fibonacci.goldenRatio(period);
        if (!expected.equals(result)) {
            parameters.getOut().println("Failed test");
        } else {
            parameters.getOut().println("Test ok");
        }
    }


    private static void matrixFibonacciTest(Test.TestRunnerParameters parameters) {
        int period = Integer.parseInt(parameters.getInputData()[0]);
        BigInteger expected = new BigInteger(parameters.getExpectedData()[0]);
        BigInteger result = Fibonacci.matrix(period);
        if (!expected.equals(result)) {
            parameters.getOut().println(String.format("Failed test, expected: %s, result: %s", expected, result));
        } else {
            parameters.getOut().println("Test ok");
        }
    }


    private static void countPrimeNumbersByEnumeratingDivisorsTest(Test.TestRunnerParameters parameters) {
        int number = Integer.parseInt(parameters.getInputData()[0]);
        int expected = Integer.parseInt(parameters.getExpectedData()[0]);
        int result = PrimeNumbers.countByEnumeratingDivisors(number, PrimeNumbers::isPrimeByEnumeratingDivisors);
        if (expected != result) {
            parameters.getOut().println(String.format("Failed test, expected: %s, result: %s", expected, result));
        } else {
            parameters.getOut().println("Test ok");
        }
    }

    private static void countPrimeNumbersByEnumeratingOnlyOddDivisors(Test.TestRunnerParameters parameters) {
        int number = Integer.parseInt(parameters.getInputData()[0]);
        int expected = Integer.parseInt(parameters.getExpectedData()[0]);
        int result = PrimeNumbers.countByEnumeratingDivisors(number, PrimeNumbers::isPrimeByEnumeratingOnlyOddDivisors);
        if (expected != result) {
            parameters.getOut().println(String.format("Failed test, expected: %s, result: %s", expected, result));
        } else {
            parameters.getOut().println("Test ok");
        }
    }

    private static void countPrimeNumbersByEnumeratingToItsSquareRoot(Test.TestRunnerParameters parameters) {
        int number = Integer.parseInt(parameters.getInputData()[0]);
        int expected = Integer.parseInt(parameters.getExpectedData()[0]);
        int result = PrimeNumbers.countByEnumeratingDivisors(number, PrimeNumbers::isPrimeByEnumeratingDivisorsToItsSquareRoot);
        if (expected != result) {
            parameters.getOut().println(String.format("Failed test, expected: %s, result: %s", expected, result));
        } else {
            parameters.getOut().println("Test ok");
        }
    }


    private static void countPrimeNumbersByEratosthenesSieve(Test.TestRunnerParameters parameters) {
        int number = Integer.parseInt(parameters.getInputData()[0]);
        int expected = Integer.parseInt(parameters.getExpectedData()[0]);
        int result = PrimeNumbers.countByEratosthenesSieve(number);
        if (expected != result) {
            parameters.getOut().println(String.format("Failed test, expected: %s, result: %s", expected, result));
        } else {
            parameters.getOut().println("Test ok");
        }
    }

    private static void countPrimeNumbersByEratosthenesSieveBitmap(Test.TestRunnerParameters parameters) {
        int number = Integer.parseInt(parameters.getInputData()[0]);
        int expected = Integer.parseInt(parameters.getExpectedData()[0]);
        int result = PrimeNumbers.countByEratosthenesSieveBitMap(number);
        if (expected != result) {
            parameters.getOut().println(String.format("Failed test, expected: %s, result: %s", expected, result));
        } else {
            parameters.getOut().println("Test ok");
        }
    }

    private static void countPrimeNumbersByEratosthenesSieveOddBitmap(Test.TestRunnerParameters parameters) {
        int number = Integer.parseInt(parameters.getInputData()[0]);
        int expected = Integer.parseInt(parameters.getExpectedData()[0]);
        int result = PrimeNumbers.countByEratosthenesSieveOddBitMap(number);
        if (expected != result) {
            parameters.getOut().println(String.format("Failed test, expected: %s, result: %s", expected, result));
        } else {
            parameters.getOut().println("Test ok");
        }
    }


    public static void main(String[] args) {
        final var tests = List.of(new Test(
                        "Simple iteration pow test", "",
                        Paths.get("./test_cases/lesson3/3.Power"),
                        0, 9,
                        App::simpleIterationPowTest
                )
                , new Test(
                        "Iteration pow test", "",
                        Paths.get("./test_cases/lesson3/3.Power"),
                        0, 9,
                        App::iterationPowTest
                )
                , new Test(
                        "Two pow test", "",
                        Paths.get("./test_cases/lesson3/3.Power"),
                        0, Integer.MAX_VALUE,
                        App::twoPowTest
                ),
                new Test(
                        "Bin pow test", "",
                        Paths.get("./test_cases/lesson3/3.Power"),
                        0, Integer.MAX_VALUE,
                        App::binPowTest
                ),
                new Test(
                        "Fibonacci simple recursion test", "",
                        Paths.get("./test_cases/lesson3/4.Fibo"),
                        0, 7,
                        App::simpleFibonacciRecursionTest
                ),
                new Test(
                        "Fibonacci iteration test", "",
                        Paths.get("./test_cases/lesson3/4.Fibo"),
                        0, 11,
                        App::iterationFibonacciRecursionTest
                ),
                new Test(
                        "Fibonacci golden ratio test", "",
                        Paths.get("./test_cases/lesson3/4.Fibo"),
                        0, 12,
                        App::goldenRatioFibonacciRecursionTest
                ),
                new Test(
                        "Fibonacci matrix test", "",
                        Paths.get("./test_cases/lesson3/4.Fibo"),
                        0, 11,
                        App::matrixFibonacciTest
                ),
                new Test(
                        "Prime numbers counting by enumerating divisors test", "",
                        Paths.get("./test_cases/lesson3/5.Primes"),
                        0, 10,
                        App::countPrimeNumbersByEnumeratingDivisorsTest
                ),
                new Test(
                        "Prime numbers counting by enumerating only odd divisors test", "",
                        Paths.get("./test_cases/lesson3/5.Primes"),
                        0, 10,
                        App::countPrimeNumbersByEnumeratingOnlyOddDivisors
                ),
                new Test(
                        "Prime numbers counting by enumerating to its square root test", "",
                        Paths.get("./test_cases/lesson3/5.Primes"),
                        0, 10,
                        App::countPrimeNumbersByEnumeratingToItsSquareRoot
                ),
                new Test(
                        "Prime numbers counting by eratosthenes sieve test", "",
                        Paths.get("./test_cases/lesson3/5.Primes"),
                        0, 10,
                        App::countPrimeNumbersByEratosthenesSieve
                ),
                new Test(
                        "Prime numbers counting by eratosthenes sieve with bitmap test", "",
                        Paths.get("./test_cases/lesson3/5.Primes"),
                        0, 10,
                        App::countPrimeNumbersByEratosthenesSieveBitmap
                ),
                new Test(
                        "Prime numbers counting by eratosthenes sieve with bitmap test", "",
                        Paths.get("./test_cases/lesson3/5.Primes"),
                        0, 10,
                        App::countPrimeNumbersByEratosthenesSieveBitmap
                ),
                new Test(
                        "Prime numbers counting by eratosthenes sieve with odd bitmap test", "",
                        Paths.get("./test_cases/lesson3/5.Primes"),
                        0, 10,
                        App::countPrimeNumbersByEratosthenesSieveOddBitmap
                )
        );
        for (var test : tests) {
            test.run();
        }
    }
}
