package ru.otus.danilchenko.algorithms.lesson21.peas;

public class Peas {
    private boolean isEven(int el) {
        return el % 2 == 0;
    }

    private int calcGCD(int first, int second) {
        if (first == second) {
            return first;
        }
        if (first == 0) {
            return second;
        }
        if (isEven(first) && isEven(second)) {
            return calcGCD(first >> 1, second >> 1) << 1;
        }
        if (isEven(first)) {
            return calcGCD(first >> 1, second);
        }
        if (isEven(second)) {
            return calcGCD(first, second >> 1);
        }
        if (first > second) {
            return calcGCD((first - second) >> 1, second);
        }
        return calcGCD(first, (second - first) >> 1);
    }

    public String calculate(String data) {
        //2/100+3/100
        int firstDivision = data.indexOf('/');
        int sum = data.indexOf('+');
        int secondDivision = data.indexOf('/', sum);
        int a = Integer.parseInt(data.substring(0, firstDivision));
        int b = Integer.parseInt(data.substring(firstDivision + 1, sum));
        int c = Integer.parseInt(data.substring(sum + 1, secondDivision));
        int d = Integer.parseInt(data.substring(secondDivision + 1));
        System.out.println(String.format("a: %d, b:%d, c:%d, d:%d", a, b, c, d));
        int first = a*d + b*c;
        int second = b*d;
        int gcd = calcGCD(first, second);
        int firstResult = first / gcd;
        int secondResult = second/ gcd;
        return String.format("%d/%d", firstResult, secondResult);
    }

}
