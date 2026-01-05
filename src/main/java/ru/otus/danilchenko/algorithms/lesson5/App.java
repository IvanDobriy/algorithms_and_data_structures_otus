package ru.otus.danilchenko.algorithms.lesson5;

import ru.otus.danilchenko.algorithms.test.Test;

import java.math.BigInteger;
import java.nio.file.Paths;
import java.util.List;

public class App {
    private final IStepsCalculator cachedCalculation = BitOnesCalculators.cachedCalculation();
    private final IStepsCalculator simpleCalculation = BitOnesCalculators::simpleCalculation;
    private final IStepsCalculator kernighanCalculation = BitOnesCalculators::kernighanCalculation;

    private void kingTest(Test.TestRunnerParameters parameters) {
        final int position = Integer.parseInt(parameters.getInputData()[0]);
        final long expectedNumberOfSteps = Long.parseLong(parameters.getExpectedData()[0]);
        final long expectedBitMask = new BigInteger(parameters.getExpectedData()[1]).longValue();//Long.parseLong(expectedData[1]);

        final ChessPiece king = new King(position, simpleCalculation);
        if (expectedBitMask != king.getStepsPosition()) {
            parameters.getOut().println(String.format("Failed test, expected bit mask: %s, result: %s", expectedBitMask, king.getStepsPosition()));
            return;
        }
        if (expectedNumberOfSteps != king.getNumberOfSteps()) {
            parameters.getOut().println(String.format("Failed test, expected number of steps: %s, result: %s", expectedNumberOfSteps, king.getNumberOfSteps()));
            return;
        }
        parameters.getOut().println("Test ok");
    }

    private void knightTest(Test.TestRunnerParameters parameters) {
        final int position = Integer.parseInt(parameters.getInputData()[0]);
        final long expectedNumberOfSteps = Long.parseLong(parameters.getExpectedData()[0]);
        final long expectedBitMask = new BigInteger(parameters.getExpectedData()[1]).longValue();
        final ChessPiece knight = new Knight(position, kernighanCalculation);
        if (expectedBitMask != knight.getStepsPosition()) {
            parameters.getOut().println(String.format("Failed test, expected bit mask: %s, result: %s", expectedBitMask, knight.getStepsPosition()));
            return;
        }
        if (expectedNumberOfSteps != knight.getNumberOfSteps()) {
            parameters.getOut().println(String.format("Failed test, expected number of steps: %s, result: %s", expectedNumberOfSteps, knight.getNumberOfSteps()));
            return;
        }
        parameters.getOut().println("Test ok");
    }

    private void rookTest(Test.TestRunnerParameters parameters) {
        final int position = Integer.parseInt(parameters.getInputData()[0]);
        final long expectedNumberOfSteps = Long.parseLong(parameters.getExpectedData()[0]);
        final long expectedBitMask = new BigInteger(parameters.getExpectedData()[1]).longValue();
        final ChessPiece rook = new Rook(position, cachedCalculation);
        if (expectedBitMask != rook.getStepsPosition()) {
            parameters.getOut().println(String.format("Failed test, expected bit mask: %s, result: %s", expectedBitMask, rook.getStepsPosition()));
            return;
        }
        if (expectedNumberOfSteps != rook.getNumberOfSteps()) {
            parameters.getOut().println(String.format("Failed test, expected number of steps: %s, result: %s", expectedNumberOfSteps, rook.getNumberOfSteps()));
            return;
        }
        parameters.getOut().println("Test ok");
    }

    private void bishopTest(Test.TestRunnerParameters parameters) {
        final int position = Integer.parseInt(parameters.getInputData()[0]);
        final long expectedNumberOfSteps = Long.parseLong(parameters.getExpectedData()[0]);
        final long expectedBitMask = new BigInteger(parameters.getExpectedData()[1]).longValue();
        final ChessPiece bishop = new Bishop(position, cachedCalculation);
        if (expectedBitMask != bishop.getStepsPosition()) {
            parameters.getOut().println(String.format("Failed test, expected bit mask: %s, result: %s", expectedBitMask, bishop.getStepsPosition()));
            return;
        }
        if (expectedNumberOfSteps != bishop.getNumberOfSteps()) {
            parameters.getOut().println(String.format("Failed test, expected number of steps: %s, result: %s", expectedNumberOfSteps, bishop.getNumberOfSteps()));
            return;
        }
        parameters.getOut().println("Test ok");
    }

    private void queenTest(Test.TestRunnerParameters parameters) {
        final int position = Integer.parseInt(parameters.getInputData()[0]);
        final long expectedNumberOfSteps = Long.parseLong(parameters.getExpectedData()[0]);
        final long expectedBitMask = new BigInteger(parameters.getExpectedData()[1]).longValue();
        final ChessPiece queen = new Queen(position, cachedCalculation);
        if (expectedBitMask != queen.getStepsPosition()) {
            parameters.getOut().println(String.format("Failed test, expected bit mask: %s, result: %s", expectedBitMask, queen.getStepsPosition()));
            return;
        }
        if (expectedNumberOfSteps != queen.getNumberOfSteps()) {
            parameters.getOut().println(String.format("Failed test, expected number of steps: %s, result: %s", expectedNumberOfSteps, queen.getNumberOfSteps()));
            return;
        }
        parameters.getOut().println("Test ok");
    }


    private void run(String[] args) {
        final String defaultCaseName = "1";
        final var tests = List.of(new Test(
                "King test",
                            defaultCaseName,
                Paths.get("./test_cases/lesson4/0.BITS/1.Bitboard - Король"),
                0, Integer.MAX_VALUE,
                this::kingTest
        ), new Test(
                "Knight test",
                defaultCaseName,
                Paths.get("./test_cases/lesson4/0.BITS/2.Bitboard - Конь"),
                0, Integer.MAX_VALUE,
                this::knightTest
        ), new Test(
                "Rook test",
                defaultCaseName,
                Paths.get("./test_cases/lesson4/0.BITS/3.Bitboard - Ладья"),
                0, Integer.MAX_VALUE,
                this::rookTest
        ), new Test(
                "Bishop test",
                defaultCaseName,
                Paths.get("./test_cases/lesson4/0.BITS/4.Bitboard - Слон"),
                0, Integer.MAX_VALUE,
                this::bishopTest
        ), new Test(
                "Queen test",
                defaultCaseName,
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
