package ru.otus.danilchenko.algorithms.lesson4.dynamic_arrays;

public class ArrayUtils {
    public static <T> T[] createArray(int size) {
        return (T[]) (new Object[size]);
    }
}
