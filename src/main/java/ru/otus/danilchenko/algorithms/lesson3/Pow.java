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

    static double twoPow(double digit, long degreeIndicator) {
        if (degreeIndicator == 0) {
            return 1.0;
        }
        if ((degreeIndicator % 2) > 0) {
            double result = twoPow(digit, degreeIndicator / 2);
            return digit * result * result;
        }
        double result = twoPow(digit, degreeIndicator / 2);
        return result * result;
    }
}