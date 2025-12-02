package ru.otus.danilchenko.algorithms.lesson5;

public class Calculators {
    public static int kernighanCalculation(long map){
        int counter = 0;
        while (map != 0) {
            map &= (map - 1);
            counter++;
        }
        return counter;
    }
}
