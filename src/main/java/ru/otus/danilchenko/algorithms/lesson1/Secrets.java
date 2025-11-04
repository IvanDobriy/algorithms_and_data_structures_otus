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
        return (xMaxSize - x) > y - 5;
    }

    public static boolean secret6(int x, int y, int xMaxSize, int yMaxSize) {
        return x / 2 - y == 0;
    }

    public static boolean secret7(int x, int y, int xMaxSize, int yMaxSize) {
        return xMaxSize - x > 15 || yMaxSize - y > 15;
    }

    public static boolean secret8(int x, int y, int xMaxSize, int yMaxSize) {
        return xMaxSize - x <= 9 && yMaxSize - y <= 9;
    }

    public static boolean secret9(int x, int y, int xMaxSize, int yMaxSize) {
        return x == 0 || y == 0;
    }

    public static boolean secret10(int x, int y, int xMaxSize, int yMaxSize) {
        return x <= (y - 11) || x >= (y + 11);
    }

    public static boolean secret11(int x, int y, int xMaxSize, int yMaxSize) {
        throw new RuntimeException("");
//        return x <= x / 2 + y && y <= y / 2 + x;//todo not working
    }

    public static boolean secret12(int x, int y, int xMaxSize, int yMaxSize) {
        return x == 1 || y == 1 || y == yMaxSize - 2 || x == xMaxSize - 2;
    }

    public static boolean secret13(int x, int y, int xMaxSize, int yMaxSize) {
        throw new RuntimeException("");
//        return x * x + y * y <= 20 * 20; //todo not working
    }

    public static boolean secret14(int x, int y, int xMaxSize, int yMaxSize) {
        return x > (yMaxSize - 1) - y - 5 && x < ((yMaxSize - 1) - y + 5);
    }

    public static boolean secret15(int x, int y, int xMaxSize, int yMaxSize) {
        throw new RuntimeException("");
    }

    public static boolean secret16(int x, int y, int xMaxSize, int yMaxSize) {
        return  (x > (y - 21) && x < (y + 21)) && (x <= (y - 10) || x >= (y + 10));
    }

}
