package ru.otus.danilchenko.algorithms.lesson2;

public class TicketsCounter {

    private static final int ROW_SIZE = 10;

    private static int getNextColumnSize(int n) {
        return n * 9 + 1;
    }

    private static int[][] calculateTable(int columnSize, int[] previousSum) {
        int[][] table = new int[ROW_SIZE][columnSize];
        for (int r = 0; r < ROW_SIZE; r++) {
            for (int c = 0; c < columnSize; c++) {
                if (c < previousSum.length) {
                    table[r][c + r] = previousSum[c % previousSum.length];
                }
            }
        }
        return table;
    }

    private static int[] calculateRowOfSum(int columnSize, int[][] table) {
        int[] rowOfSum = new int[columnSize];
        for (int c = 0; c < columnSize; c++) {
            for (int r = 0; r < ROW_SIZE; r++) {
                rowOfSum[c] += table[r][c];
            }
        }
        return rowOfSum;
    }

    private static long calculateTicketsCounter(int[] rowOfSum) {
        long result = 0;
        for (int j = 0; j < rowOfSum.length; j++) {
            result += (long) rowOfSum[j] * rowOfSum[j];
        }
        return result;
    }

    public static long count(int nValue) {
        int[] previousRowOfSum = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
        long result = 10;
        int columnSize;
        for (int numberOfDigit = 2; numberOfDigit <= nValue; numberOfDigit++) {
            columnSize = getNextColumnSize(numberOfDigit);
            int[][] table = calculateTable(columnSize, previousRowOfSum);
            previousRowOfSum = calculateRowOfSum(columnSize, table);
            result = calculateTicketsCounter(previousRowOfSum);
        }
        return result;
    }
}
