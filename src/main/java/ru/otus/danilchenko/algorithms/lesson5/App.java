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

        final ChessPiece king = new King(position);
        if (expectedBitMask != king.getMovesPosition()) {
            out.println(String.format("Failed test, expected bit mask: %s, result: %s", expectedBitMask, king.getMovesPosition()));
        } else {
            out.println("Test ok");
        }

        if (expectedNumberOfMoves != king.getNumberOfMoves()) {
            out.println(String.format("Failed test, expected number of moves: %s, result: %s", expectedBitMask, king.getNumberOfMoves()));
        } else {
            out.println("Test ok");
        }

    }

    private void run(String[] args) {
        final var tests = List.of(new Test(
                "Simple iteration pow test",
                Paths.get("./test_cases/lesson4/0.BITS/1.Bitboard - Король"),
                0, 9,
                this::kingTest
        ));

        for(var test: tests){
            test.run();
        }
    }

    public static void main(String[] args) {
        final App app = new App();
        app.run(args);
    }
}
