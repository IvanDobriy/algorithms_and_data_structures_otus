package ru.otus.danilchenko.algorithms.lesson3;

import java.math.BigInteger;

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
        if(period.equals(BigInteger.ZERO)){
            return BigInteger.ZERO;
        }
        BigInteger previous = BigInteger.ONE;
        BigInteger next = BigInteger.ONE;
        BigInteger sumOfPeriods;
        BigInteger i = period;
        while (i.compareTo(BigInteger.TWO) > 0) {
            sumOfPeriods = previous.add(next);
            next = sumOfPeriods;
            previous = next;
            i = i.subtract(BigInteger.ONE);
        }
        return next;
    }
}
