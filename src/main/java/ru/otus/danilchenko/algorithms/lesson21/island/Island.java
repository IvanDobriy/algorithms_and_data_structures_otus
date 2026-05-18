package ru.otus.danilchenko.algorithms.lesson21.island;

public class Island {
    private int[] getRow(String line) {
        String[] numbers = line.split(" ");
        int[] result = new int[numbers.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = Integer.parseInt(numbers[i]);
        }
        return result;
    }

    private void dfcZero(int row, int col, int[][] matrix) {
        if (matrix[row][col] == 0) {
            return;
        }
        matrix[row][col] = 0;
        if (row > 0) {
            dfcZero(row - 1, col, matrix);
        }
        if (row < matrix.length - 1) {
            dfcZero(row + 1, col, matrix);
        }
        if (col > 0) {
            dfcZero(row, col - 1, matrix);
        }
        if (col < matrix.length - 1) {
            dfcZero(row, col + 1, matrix);
        }
    }

    public String calculate(String[] data) {
        int result = 0;
        final int length = Integer.parseInt(data[0]);
        final int[][] island = new int[length][];
        for (int i = 0; i < length; i++) {
            String line = data[i + 1].trim();
            island[i] = getRow(line);
        }
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if (island[i][j] == 1) {
                    dfcZero(i, j, island);
                    result++;
                }
            }
        }
        return "" + result;
    }
}
