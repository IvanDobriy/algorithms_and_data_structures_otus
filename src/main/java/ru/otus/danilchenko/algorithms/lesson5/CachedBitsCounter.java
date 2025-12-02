package ru.otus.danilchenko.algorithms.lesson5;

public class CachedBitsCounter implements IStepsCalculator {
    private byte[] cache;

    public CachedBitsCounter() {
        cache = new byte[256];
        for (int i = 0; i < cache.length; i++) {
            cache[i] = (byte) BitOnesCalculators.kernighanCalculation(i);
        }
    }

    @Override
    public int calculate(long map) {
        int counter = 0;
        int firstHalf = (int) (map & 0xffffffffL);
        int secondHalf = (int) (BitOps.rSh(map, 8) & 0xffffffffL);

        for (int i = 0; i < 4; i++) {
            counter += cache[(int) BitOps.rSh(firstHalf, i) & 0xff];
        }
        for (int i = 0; i < 4; i++) {
            counter += cache[(int) BitOps.rSh(secondHalf, i) & 0xff];
        }
        return counter;
    }
}
