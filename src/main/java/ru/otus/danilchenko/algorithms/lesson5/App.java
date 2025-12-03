package ru.otus.danilchenko.algorithms.lesson5;

import ru.otus.danilchenko.algorithms.test.Test;

import java.io.PrintStream;
import java.math.BigInteger;
import java.nio.file.Paths;
import java.util.List;

public class App {
    private final IStepsCalculator cachedCalculation = BitOnesCalculators.cachedCalculation();
    private final IStepsCalculator simpleCalculation = BitOnesCalculators::simpleCalculation;
    private final IStepsCalculator kernighanCalculation = BitOnesCalculators::kernighanCalculation;

    private void kingTest(String[] inputData, String[] expectedData, PrintStream out) {
        final int position = Integer.parseInt(inputData[0]);
        final long expectedNumberOfSteps = Long.parseLong(expectedData[0]);
        final long expectedBitMask = new BigInteger(expectedData[1]).longValue();//Long.parseLong(expectedData[1]);

        final ChessPiece king = new King(position, simpleCalculation);
        if (expectedBitMask != king.getStepsPosition()) {
            out.println(String.format("Failed test, expected bit mask: %s, result: %s", expectedBitMask, king.getStepsPosition()));
            return;
        }
        if (expectedNumberOfSteps != king.getNumberOfSteps()) {
            out.println(String.format("Failed test, expected number of steps: %s, result: %s", expectedNumberOfSteps, king.getNumberOfSteps()));
            return;
        }
        out.println("Test ok");
    }

    private void knightTest(String[] inputData, String[] expectedData, PrintStream out) {
        final int position = Integer.parseInt(inputData[0]);
        final long expectedNumberOfSteps = Long.parseLong(expectedData[0]);
        final long expectedBitMask = new BigInteger(expectedData[1]).longValue();
        final ChessPiece knight = new Knight(position, kernighanCalculation);
        if (expectedBitMask != knight.getStepsPosition()) {
            out.println(String.format("Failed test, expected bit mask: %s, result: %s", expectedBitMask, knight.getStepsPosition()));
            return;
        }
        if (expectedNumberOfSteps != knight.getNumberOfSteps()) {
            out.println(String.format("Failed test, expected number of steps: %s, result: %s", expectedNumberOfSteps, knight.getNumberOfSteps()));
            return;
        }
        out.println("Test ok");
    }

    private void rookTest(String[] inputData, String[] expectedData, PrintStream out) {
        final int position = Integer.parseInt(inputData[0]);
        final long expectedNumberOfSteps = Long.parseLong(expectedData[0]);
        final long expectedBitMask = new BigInteger(expectedData[1]).longValue();
        final ChessPiece rook = new Rook(position, cachedCalculation);
        if (expectedBitMask != rook.getStepsPosition()) {
            out.println(String.format("Failed test, expected bit mask: %s, result: %s", expectedBitMask, rook.getStepsPosition()));
            return;
        }
        if (expectedNumberOfSteps != rook.getNumberOfSteps()) {
            out.println(String.format("Failed test, expected number of steps: %s, result: %s", expectedNumberOfSteps, rook.getNumberOfSteps()));
            return;
        }
        out.println("Test ok");
    }

    private void bishopTest(String[] inputData, String[] expectedData, PrintStream out) {
        final int position = Integer.parseInt(inputData[0]);
        final long expectedNumberOfSteps = Long.parseLong(expectedData[0]);
        final long expectedBitMask = new BigInteger(expectedData[1]).longValue();
        final ChessPiece bishop = new Bishop(position, kernighanCalculation);
        if (expectedBitMask != bishop.getStepsPosition()) {
            out.println(String.format("Failed test, expected bit mask: %s, result: %s", expectedBitMask, bishop.getStepsPosition()));
            return;
        }
        if (expectedNumberOfSteps != bishop.getNumberOfSteps()) {
            out.println(String.format("Failed test, expected number of steps: %s, result: %s", expectedNumberOfSteps, bishop.getNumberOfSteps()));
            return;
        }
        out.println("Test ok");
    }

    private void queenTest(String[] inputData, String[] expectedData, PrintStream out) {
        final int position = Integer.parseInt(inputData[0]);
        final long expectedNumberOfSteps = Long.parseLong(expectedData[0]);
        final long expectedBitMask = new BigInteger(expectedData[1]).longValue();
        final ChessPiece queen = new Queen(position, kernighanCalculation);
        if (expectedBitMask != queen.getStepsPosition()) {
            out.println(String.format("Failed test, expected bit mask: %s, result: %s", expectedBitMask, queen.getStepsPosition()));
            return;
        }
        if (expectedNumberOfSteps != queen.getNumberOfSteps()) {
            out.println(String.format("Failed test, expected number of steps: %s, result: %s", expectedNumberOfSteps, queen.getNumberOfSteps()));
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
        ), new Test(
                "Rook test",
                Paths.get("./test_cases/lesson4/0.BITS/3.Bitboard - Ладья"),
                0, Integer.MAX_VALUE,
                this::rookTest
        ), new Test(
                "Bishop test",
                Paths.get("./test_cases/lesson4/0.BITS/4.Bitboard - Слон"),
                0, Integer.MAX_VALUE,
                this::bishopTest
        ), new Test(
                "Queen test",
                Paths.get("./test_cases/lesson4/0.BITS/5.Bitboard - Ферзь"),
                0, Integer.MAX_VALUE,
                this::queenTest
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
