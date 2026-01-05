package ru.otus.danilchenko.algorithms.lesson3;

public class Pow {
    public static double simpleIteration(double digit, long degreeIndicator) {
        double result = 1.0;
        if (degreeIndicator == 0) {
            return result;
        }
        for (int degree = 0; degree < degreeIndicator; degree++) {
            result *= digit;
        }
        return result;
    }

    public static double iteration(double digit, long degreeIndicator) {
        double result = 1.0;
        if (degreeIndicator == 0) {
            return result;
        }
        for (int degree = 0; degree < (degreeIndicator >> 1); degree++) {
            result *= digit;
        }
        result *= result;
        if (degreeIndicator % 2 > 0) {
            result *= digit;
        }
        return result;
    }

    public static double two(double digit, long degreeIndicator) {
        double result = 1.0;
        if (degreeIndicator == 0) {
            return result;
        }
        if ((degreeIndicator % 2) > 0) {
            result = two(digit, degreeIndicator >> 1);
            return digit * result * result;
        }
        result = two(digit, degreeIndicator >> 1);
        return result * result;
    }

    public static double bin(double digit, long degreeIndicator) {
        double result = 1.0;
        if (degreeIndicator == 0) {
            return result;
        }
        long mask = 0x01L << 63;
        while ((degreeIndicator & mask) == 0) {
            mask = (mask >> 1) & ~mask;
        }
        boolean needMultiplyByDigit;
        while (mask > 0) {
            needMultiplyByDigit = false;
            if ((degreeIndicator & mask) > 0) {
                needMultiplyByDigit = true;
            }
            result *= result;
            if (needMultiplyByDigit) {
                result *= digit;
            }
            mask = (mask >> 1) & ~mask;
        }
        return result;
    }
}