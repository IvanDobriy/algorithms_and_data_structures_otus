package ru.otus.danilchenko.algorithms.lesson22;


import ru.otus.danilchenko.algorithms.lesson22.hll.HyperLogLog;

public class App {
    private void run() {
        HyperLogLog hll = new HyperLogLog(14);

        int exactCount = 1_000_000;
        for (int i = 0; i < exactCount; i++) {
            hll.add("user-" + (i % exactCount));
        }
        for (int i = 0; i < 500_000; i++) {
            hll.add("user-" + (i % exactCount));
        }

        long estimate = hll.count();
        double error = Math.abs(estimate - exactCount) / (double) exactCount * 100;

        System.out.println("Точное количество:    " + exactCount);
        System.out.println("Оценка HyperLogLog:   " + estimate);
        System.out.printf("Относительная ошибка: %.3f%%%n", error);
        System.out.println("Размер состояния:     " + hll.size() + " байт");
    }

    public static void main(String[] args) {
        final var app = new App();
        app.run();
    }
}
