package ru.otus.danilchenko.algorithms.lesson4.dynamic_arrays;

public class ArrayUtils {
    public static <T> T[] createArray(int size) {
        return (T[]) (new Object[size]);
    }

    public static <T> void copyArray(T[] from, T[] to) {
        if (from.length > to.length) {
            throw new IllegalArgumentException("from.length must be more than to.length");
        }
        for (int i = 0; i < from.length; i++) {
            to[i] = from[i];
        }
    }
}
