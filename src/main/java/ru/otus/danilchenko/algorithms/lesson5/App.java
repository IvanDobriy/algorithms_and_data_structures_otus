package ru.otus.danilchenko.algorithms.lesson5;

import ru.otus.danilchenko.algorithms.test.Test;

import java.io.PrintStream;
import java.math.BigInteger;
import java.nio.file.Paths;
import java.util.List;

public class App {
    private void kingTest(String[] inputData, String[] expectedData, PrintStream out) {
        final long position = Long.parseLong(inputData[0]);
        final long expectedNumberOfMoves = Long.parseLong(expectedData[0]);
        final long expectedBitMask = new BigInteger(expectedData[1]).longValue();//Long.parseLong(expectedData[1]);

        final ChessPiece king = new King(position, Calculators::kernighanCalculation);
        if (expectedBitMask != king.getStepsPosition()) {
            out.println(String.format("Failed test, expected bit mask: %s, result: %s", expectedBitMask, king.getStepsPosition()));
            return;
        }
        if (expectedNumberOfMoves != king.getNumberOfSteps()) {
            out.println(String.format("Failed test, expected number of moves: %s, result: %s", expectedBitMask, king.getNumberOfSteps()));
            return;
        }
        out.println("Test ok");
    }

    private void knightTest(String[] inputData, String[] expectedData, PrintStream out) {
        final long position = Long.parseLong(inputData[0]);
        final long expectedNumberOfMoves = Long.parseLong(expectedData[0]);
        final long expectedBitMask = new BigInteger(expectedData[1]).longValue();
        final ChessPiece knight = new Knight(position);
        if (expectedBitMask != knight.getStepsPosition()) {
            out.println(String.format("Failed test, expected bit mask: %s, result: %s", expectedBitMask, knight.getStepsPosition()));
            return;
        }
        if (expectedNumberOfMoves != knight.getNumberOfSteps()) {
            out.println(String.format("Failed test, expected number of moves: %s, result: %s", expectedBitMask, knight.getNumberOfSteps()));
            return;
        }
        out.println("Test ok");
    }

    private void run(String[] args) {
        final var tests = List.of(new Test(
                "King test",
                Paths.get("./test_cases/lesson4/0.BITS/1.Bitboard - Король"),
                0, Integer.MAX_VALUE,
                this::kingTest
        ), new Test(
                "Knight test",
                Paths.get("./test_cases/lesson4/0.BITS/2.Bitboard - Конь"),
                0, Integer.MAX_VALUE,
                this::knightTest
        ));

        for (var test : tests) {
            test.run();
        }
    }

    public static void main(String[] args) {
        final App app = new App();
        app.run(args);
    }
}
