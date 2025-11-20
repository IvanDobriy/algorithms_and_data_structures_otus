package ru.otus.danilchenko.algorithms.lesson3;

public class PrimeNumbers {
    public interface PrimeChecking {
        boolean isPrime(int number);
    }

    public static boolean isPrimeByEnumeratingDivisors(int number) {
        if (number < 2) {
            return false;
        }
        for (int p = 2; p < number; p++) {// на 0 делить нельзя, а с 1 и так все понятно
            if (number % p == 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean isPrimeByEnumeratingOnlyOddDivisors(int number) {
        if (number < 2) {
            return false;
        }
        if (number == 2) {
            return true;
        }
        if(number % 2 == 0){
            return false;
        }
        for (int i = 3; i < number; i += 2) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static int countByEnumeratingDivisors(int n, PrimeChecking check) {
        int counter = 0;
        for (int i = 1; i < n; i++) {
            if (check.isPrime(i)) {
                counter++;
            }
        }
        return counter;
    }
}
