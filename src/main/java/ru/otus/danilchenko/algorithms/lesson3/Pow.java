package ru.otus.danilchenko.algorithms.lesson3;

public class Pow {
    static double simpleIterationPow(double digit, long degreeIndicator) {
        double result = 1.0;
        if (degreeIndicator == 0) {
            return result;
        }
        for (int degree = 0; degree < degreeIndicator; degree++) {
            result *= digit;
        }
        return result;
    }

    static double twoPow(double digit, long degreeIndicator) {
        double result = 1.0;
        if (degreeIndicator == 0) {
            return result;
        }
        if ((degreeIndicator % 2) > 0) {
            result = twoPow(digit, degreeIndicator >> 1);
            return digit * result * result;
        }
        result = twoPow(digit, degreeIndicator >> 1);
        return result * result;
    }

    static double binPow(double digit, long degreeIndicator) {
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