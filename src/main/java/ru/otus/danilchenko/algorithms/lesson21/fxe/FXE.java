package ru.otus.danilchenko.algorithms.lesson21.fxe;

//5*8
public class FXE {
    long calcFib(int n) {
        if (n < 2) {
            return n;
        }
        long previous = 0;
        long current = 1;
        long newValue = 0;
        for (int i = 0; i < n; i++) {
            newValue = previous + current;
            previous = current;
            current = newValue;
        }
        return current;
    }

    public String calculate(String data) {
        int n = Integer.parseInt(data);
        return "" + calcFib(n)*2;
    }
}
