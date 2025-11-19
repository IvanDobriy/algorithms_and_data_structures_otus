package ru.otus.danilchenko.algorithms.lesson3;

public class PrimeNumbers {
    private static boolean isPrimeByEnumeratingDivisors(int number) {
        for (int p = 2; p < number; p++) {// на 0 делить нельзя, а с 1 и так все понятно
            if (number % p == 0) {
                return false;
            }
        }
        return true;
    }

    public static int countByEnumeratingDivisors(int n) {
        int counter = 0;
        for (int i = 1; i < n; i++) {
            if (isPrimeByEnumeratingDivisors(n)) {
                counter++;
            }
        }
        return counter;
    }
}
