package ru.otus.danilchenko.algorithms.lesson21.tree;

public class Tree {

    private int[] getRow(String line) {
        String[] numbers = line.split(" ");
        int[] result = new int[numbers.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = Integer.parseInt(numbers[i]);
        }
        return result;
    }


    public String calculate(String[] data) {
        final int length = Integer.parseInt(data[0]);
        final int[][] tree = new int[length][];
        for (int i = 0; i < length; i++) {
            String line = data[i + 1].trim();
            tree[i] = getRow(line);
        }

        for (int i = length - 2; i >= 0; i--) {
            for (int j = 0; j < i + 1; j++) {
                tree[i][j] += Math.max(tree[i + 1][j], tree[i + 1][j + 1]);
            }
        }
        return "" + tree[0][0];
    }
}
