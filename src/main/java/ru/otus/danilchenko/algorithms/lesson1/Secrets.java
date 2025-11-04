package ru.otus.danilchenko.algorithms.lesson1;

public class Secrets {
    public static boolean defaultSecret(int x, int y, int maxSize) {
        return true;
    }

    public static boolean secret1(int x, int y, int maxSize) {
        return x > y;
    }

    public static boolean secret2(int x, int y, int maxSize) {
        return y % 6 == 0 || x % 6 == 0;
    }

    public static boolean secret3(int x, int y, int maxSize) {
        return x == y;
    }

    public static boolean secret4(int x, int y, int maxSize) {
        return maxSize - 1 - x == y;
    }

    public static boolean secret5(int x, int y, int maxSize) {
        return (maxSize - x) > y - 5;
    }

    public static boolean secret6(int x, int y, int maxSize) {
        return x / 2 - y == 0;
    }

    public static boolean secret7(int x, int y, int maxSize) {
        return maxSize - x > 15 || maxSize - y > 15;
    }

    public static boolean secret8(int x, int y, int maxSize) {
        return maxSize - x <= 9 && maxSize - y <= 9;
    }

    public static boolean secret9(int x, int y, int maxSize) {
        return x == 0 || y == 0;
    }

    public static boolean secret10(int x, int y, int maxSize) {
        return x <= (y - 11) || x >= (y + 11);
    }

    public static boolean secret11(int x, int y, int maxSize) {
        throw new RuntimeException("NOT done");
//        return x <= x / 2 + y && y <= y / 2 + x;//todo not working
    }

    public static boolean secret12(int x, int y, int maxSize) {
        return x == 1 || y == 1 || y == maxSize - 2 || x == maxSize - 2;
    }

    public static boolean secret13(int x, int y, int maxSize) {
        throw new RuntimeException("NOT done");
//        return x * x + y * y <= 20 * 20; //todo not working
    }

    public static boolean secret14(int x, int y, int maxSize) {
        return x > (maxSize - 1) - y - 5 && x < ((maxSize - 1) - y + 5);
    }

    public static boolean secret15(int x, int y, int maxSize) {
        throw new RuntimeException("NOT done");
    }

    public static boolean secret16(int x, int y, int maxSize) {
        return (x > (y - 21) && x < (y + 21)) && (x <= (y - 10) || x >= (y + 10));
    }

    //16 and 10
    public static boolean secret17(int x, int y, int maxSize) {
        return !(maxSize - 1 - x <= (y - 10) || maxSize - 1 - x >= (y + 10) || x <= (y - 10) || x >= (y + 10));
    }

    public static boolean secret18(int x, int y, int maxSize) {
        throw new RuntimeException("NOT done");
    }

    public static boolean secret19(int x, int y, int maxSize) {
        return !(x == 0 && y == 0) && ((x == 0 || y == 0) || (x == 1 || y == 1));
    }

    public static boolean secret20(int x, int y, int maxSize) {
        return x == 0 || y == 0 || x == maxSize - 1 || y == maxSize - 1;
    }

    public static boolean secret21(int x, int y, int maxSize) {
        return (x - y) % 2 == 0;
    }

    public static boolean secret22(int x, int y, int maxSize) {
        throw new RuntimeException("NOT done");
    }

    public static boolean secret23(int x, int y, int maxSize) {
        return (maxSize - 1 - x - y) % 3 == 0;
    }

    public static boolean secret24(int x, int y, int maxSize) {
        return x % 2 + y % 3 == 0;
    }

    public static boolean secret25(int x, int y, int maxSize) {
        return secret4(x, y, maxSize) || secret3(x, y, maxSize);
    }

}
