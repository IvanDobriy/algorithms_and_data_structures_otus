package ru.otus.danilchenko.algorithms.lesson3;

import java.math.BigInteger;

public class Fibonacci {
    public static BigInteger simpleRecursion(BigInteger period) {
        if (period.equals(BigInteger.ONE) || period.equals(BigInteger.TWO)) {
            return BigInteger.valueOf(1);
        }
        return simpleRecursion(period.subtract(BigInteger.ONE)).add(simpleRecursion(period.subtract(BigInteger.TWO)));
    }
}
