package ru.otus.danilchenko.algorithms.lesson3;

public class Pow {
    static double simpleIterationPow(double digit, long degreeIndicator) {
        if (degreeIndicator == 0) {
            return 1.0;
        }
        double result = 1.0;
        for (int degree = 0; degree < degreeIndicator; degree++) {
            result *= digit;
        }
        return result;
    }
}