package ru.otus.danilchenko.algorithms.lesson5;

import ru.otus.danilchenko.algorithms.test.Test;

import java.io.PrintStream;
import java.nio.file.Paths;
import java.util.List;

public class App {
    private void kingTest(String[] inputData, String[] expectedData, PrintStream out) {
        final long position = Long.parseLong(inputData[0]);
        final long expectedNumberOfMoves = Long.parseLong(expectedData[0]);
        final long expectedBitMask = Long.parseLong(expectedData[1]);

    }

    private void run(String[] args) {
        final var tests = List.of(new Test(
                "Simple iteration pow test",
                Paths.get("./test_cases/lesson4/0.BITS/1.Bitboard - Король"),
                0, 9,
                this::kingTest
        ));
    }

    public static void main(String[] args) {
        final App app = new App();
        app.run(args);
    }
}
