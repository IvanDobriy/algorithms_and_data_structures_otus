package ru.otus.danilchenko.algorithms.lesson5;

public class BitOnesCalculators {
    public static int simpleCalculation(long map) {
        int counter = 0;
        while (map != 0) {
            if ((map & 0x01L) > 0) {
                counter++;
            }
            map = BitOps.rSh(map, 1);
        }
        return counter;
    }

    public static int kernighanCalculation(long map) {
        int counter = 0;
        while (map != 0) {
            map &= (map - 1);
            counter++;
        }
        return counter;
    }
}
