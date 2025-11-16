package ru.otus.danilchenko.algorithms.test;

public class DoubleComparator {
    private static double epsilon = 0.000001d;

    public static boolean equals(double v1, double v2) {
        return Math.abs(v1 - v2) < epsilon;
    }
}
