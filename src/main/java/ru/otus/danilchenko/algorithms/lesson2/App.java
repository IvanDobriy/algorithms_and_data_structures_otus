package ru.otus.danilchenko.algorithms.lesson2;

import java.io.PrintStream;
import java.nio.file.Paths;

public class App {
    private static void stringTest(String[] inputData, String[] expectedData, PrintStream out) {
        int length = Integer.parseInt(expectedData[0]);
        String string = inputData[0];
        if (string.length() != length) {
            out.println(String.format("Failed test, expected: %d actualL %d", length, string.length()));
        } else {
            out.println("Test is ok");
        }
    }

    public static void main(String[] args) {
        final var test = new Test(
                Paths.get("./test_cases/lesson2/0.String"),
                0, Integer.MAX_VALUE,
                App::stringTest);
        test.run();
    }
}
