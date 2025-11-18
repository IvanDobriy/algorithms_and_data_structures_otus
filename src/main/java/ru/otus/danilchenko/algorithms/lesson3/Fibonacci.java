package ru.otus.danilchenko.algorithms.lesson3;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;

public class Fibonacci {
    public static BigInteger simpleRecursion(BigInteger period) {
        if (period.equals(BigInteger.ZERO)) {
            return BigInteger.ZERO;
        }
        if (period.equals(BigInteger.ONE) || period.equals(BigInteger.TWO)) {
            return BigInteger.ONE;
        }
        return simpleRecursion(period.subtract(BigInteger.ONE)).add(simpleRecursion(period.subtract(BigInteger.TWO)));
    }

    public static BigInteger iteration(BigInteger period) {
        if (period.equals(BigInteger.ZERO)) {
            return BigInteger.ZERO;
        }
        BigInteger previous = BigInteger.ONE;
        BigInteger next = BigInteger.ONE;
        BigInteger sumOfPeriods;
        BigInteger i = period;
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
}