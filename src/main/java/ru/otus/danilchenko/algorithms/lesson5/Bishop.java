package ru.otus.danilchenko.algorithms.lesson5;

import java.util.Objects;

public class Bishop implements ChessPiece {
    private final int position;
    private final int numberOfSteps;
    private final long stepsPosition;

    private final int SIZE = 8;

    private int countIterator(int columnNumber, int rowNumber) {
        int side = columnNumber;
        if (side < rowNumber) {
            side = rowNumber;
        }
        if (side == 0) {
            return side;
        }
        return (int) (side - 1);
    }


    private int countA(int columnNumber, int rowNumber) {
        return Math.min(SIZE - rowNumber, columnNumber + 1) - 1;
    }

    private int countB(int columnNumber, int rowNumber) {
        return Math.min(rowNumber + 1, SIZE - columnNumber) - 1;
    }

    private int countC(int columnNumber, int rowNumber) {
        return Math.min(SIZE - rowNumber, SIZE - columnNumber) - 1;
    }

    private int countD(int columnNumber, int rowNumber) {
        return Math.min(rowNumber + 1, columnNumber + 1) - 1;
    }

    private long calculateMovesPosition(int position) {
        int columnNumber = position % SIZE;
        int rowNumber = position / SIZE;
        long positionMap = 1L << position;
        long result = 0;

        for (int i = 0; i < countA(columnNumber, rowNumber); i++) {
            result |= BitOps.lSh(positionMap, (i + 1) * 7);
        }
        for (int i = 0; i < countC(columnNumber, rowNumber); i++) {
            result |= BitOps.lSh(positionMap, (i + 1) * 9);
        }
        for (int i = 0; i < countD(columnNumber, rowNumber); i++) {
            result |= BitOps.rSh(positionMap, (i + 1) * 9);
        }
        for (int i = 0; i < countB(columnNumber, rowNumber); i++) {
            result |= BitOps.rSh(positionMap, (i + 1) * 7);
        }
        return result;
    }

    public Bishop(int position, IStepsCalculator calculator) {
        Objects.requireNonNull(calculator);
        if (position < 0 || position > 63) {
            throw new IllegalArgumentException("position must be in range 0..63");
        }
        this.position = position;
        stepsPosition = calculateMovesPosition(position);
        numberOfSteps = calculator.calculate(stepsPosition);
    }

    @Override
    public int getNumberOfSteps() {
        return numberOfSteps;
    }

    @Override
    public int getPosition() {
        return position;
    }

    @Override
    public long getStepsPosition() {
        return stepsPosition;
    }
}
