package ru.otus.danilchenko.algorithms.lesson2;

public class TicketsCounter {

    private static int getNextSumArraySize(int n) {
        return n * 9 + 1;
    }

    public static long count(int nValue) {
        int[] previousSum = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
        long result = 10;
        for (int i = 2; i <= nValue; i++) {
            result = 0;
            int[] nextSum = new int[getNextSumArraySize(i)];
            int[][] table = new int[10][getNextSumArraySize(i)];
            for (int j = 0; j < table.length; j++) {
                for (int k = 0; k < table[0].length; k++) {
                    if (k < previousSum.length) {
                        table[j][k + j] = previousSum[k % previousSum.length];
                    }
                }
            }
            for (int k = 0; k < table[0].length; k++) {
                for (int j = 0; j < table.length; j++) {
                    nextSum[k] += table[j][k];
                }
            }
            for (int j = 0; j < nextSum.length; j++) {
                result += (long) nextSum[j] * nextSum[j];
            }
            previousSum = nextSum;
        }
        return result;
    }
}
