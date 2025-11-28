package ru.otus.danilchenko.algorithms.lesson3;

import java.math.BigInteger;

public class Fibonacci {
    private static BigInteger simpleRecursionAlgorithm(BigInteger period) {
        if (period.equals(BigInteger.ZERO)) {
            return BigInteger.ZERO;
        }
        if (period.equals(BigInteger.ONE) || period.equals(BigInteger.TWO)) {
            return BigInteger.ONE;
        }
        return simpleRecursionAlgorithm(period.subtract(BigInteger.ONE)).add(simpleRecursionAlgorithm(period.subtract(BigInteger.TWO)));
    }

    public static BigInteger simpleRecursion(int period) {
        return simpleRecursionAlgorithm(BigInteger.valueOf(period));
    }

    static BigInteger iteration(int period) {
        if (period == 0) {
            return BigInteger.ZERO;
        }
        BigInteger previous = BigInteger.ONE;
        BigInteger next = BigInteger.ONE;
        BigInteger sumOfPeriods;
        BigInteger i = BigInteger.valueOf(period);
        while (i.compareTo(BigInteger.TWO) > 0) {
            sumOfPeriods = previous.add(next);
            previous = next;
            next = sumOfPeriods;
            i = i.subtract(BigInteger.ONE);
        }
        return next;
    }

    public static BigInteger goldenRatio(int period) { //todo not working
        double fi = (1 + Math.sqrt(5)) / 2.0;
        return BigInteger.valueOf((long) Math.floor(Math.pow(fi, period) / Math.sqrt(5) + 1.0 / 2.0));
    }

    private static BigInteger[][] multiplyMatrix(BigInteger[][] leftMatrix, BigInteger[][] rightMatrix) {
        //todo add matrix multiply check
        if (leftMatrix.length != rightMatrix.length) {
            throw new RuntimeException("matrix must be equals");
        }
        for (int i = 0; i < leftMatrix.length; i++) {
            if (leftMatrix[i].length != rightMatrix[i].length) {
                throw new RuntimeException("matrix must be equals");
            }
        }

        BigInteger[][] result = new BigInteger[leftMatrix.length][rightMatrix[0].length];
        for (int i = 0; i < leftMatrix.length; i++) {
            for (int j = 0; j < rightMatrix[0].length; j++) {
                BigInteger newValue = BigInteger.ZERO;
                for (int k = 0; k < leftMatrix[0].length; k++) {
                    newValue = newValue.add(leftMatrix[i][k].multiply(rightMatrix[k][j]));
                }
                result[i][j] = newValue;
            }
        }
        return result;
    }

    private static BigInteger[][] powMatrix(BigInteger[][] matrix, int degreeIndicator) {
        if (degreeIndicator <= 1) {
            return matrix;
        }
        BigInteger[][] result = powMatrix(matrix, degreeIndicator /2);
        if (degreeIndicator % 2 > 0) {
            return multiplyMatrix(multiplyMatrix(result, result), matrix);
        }
        return multiplyMatrix(result, result);
    }

    public static BigInteger matrix(int period) {
        if (period == 0) {
            return BigInteger.ZERO;
        }
        BigInteger[][] intial = {{BigInteger.ONE, BigInteger.ONE}, {BigInteger.ONE, BigInteger.ZERO}};
        return powMatrix(intial, period - 1)[0][0];
    }
}