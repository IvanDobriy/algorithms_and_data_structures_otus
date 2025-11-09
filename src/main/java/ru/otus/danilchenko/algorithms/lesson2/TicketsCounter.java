package ru.otus.danilchenko.algorithms.lesson2;

public class TicketsCounter {

    private static final int rowSize = 10;

    private static int getNextColumnSize(int n) {
        return n * 9 + 1;
    }

    public static long count(int nValue) {
        int[] previousSum = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
        long result = 10;
        int columnSize;
        for (int i = 2; i <= nValue; i++) {
            result = 0;
            columnSize = getNextColumnSize(i);
            int[] currentSum = new int[columnSize];
            int[][] table = new int[rowSize][columnSize];
            for (int r = 0; r < rowSize; r++) {
                for (int c = 0; c < columnSize; c++) {
                    if (c < previousSum.length) {
                        table[r][c + r] = previousSum[c % previousSum.length];
                    }
                }
            }
            for (int c = 0; c < columnSize; c++) {
                for (int r = 0; r < rowSize; r++) {
                    currentSum[c] += table[r][c];
                }
            }
            for (int j = 0; j < currentSum.length; j++) {
                result += (long) currentSum[j] * currentSum[j];
            }
            previousSum = currentSum;
        }
        return result;
    }
}
