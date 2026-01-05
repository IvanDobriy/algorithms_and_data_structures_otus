package ru.otus.danilchenko.algorithms.lesson9;

import java.nio.file.Paths;

public class App {
    public static void main(String[] args) {
        final RandomGenerator generator = new RandomGenerator();
        generator.generate(10, Paths.get("./random/test.txt"));
    }
}
