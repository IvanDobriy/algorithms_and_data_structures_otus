package ru.otus.danilchenko.algorithms.lesson5;

public class BitOps {
    public static long lSh(long l, long r) {
        return l << r;
    }

    public static long rSh(long l, long r) {
        if (l < 0) {
            return (l >> r) ^ (l >> (r - 1));
        }
        return l >> r;
    }
}
