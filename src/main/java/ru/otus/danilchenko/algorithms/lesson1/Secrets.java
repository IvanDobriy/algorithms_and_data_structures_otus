package ru.otus.danilchenko.algorithms.lesson1;

public class Secrets {
    public static boolean defaultSecret(int x, int y, int xMaxSize, int yMaxSize) {
        return true;
    }

    public static boolean secret1(int x, int y, int xMaxSize, int yMaxSize) {
        return x > y;
    }

    public static boolean secret2(int x, int y, int xMaxSize, int yMaxSize) {
        return y % 6 == 0 || x % 6 == 0;
    }

    public static boolean secret3(int x, int y, int xMaxSize, int yMaxSize) {
        return x == y;
    }

    public static boolean secret4(int x, int y, int xMaxSize, int yMaxSize) {
        return xMaxSize - 1 - x == y;
    }

    public static boolean secret5(int x, int y, int xMaxSize, int yMaxSize) {
        return (xMaxSize - x)  > y - 5;
    }
}
