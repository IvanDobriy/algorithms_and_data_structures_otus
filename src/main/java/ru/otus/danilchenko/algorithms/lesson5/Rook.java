package ru.otus.danilchenko.algorithms.lesson5;

import java.util.Objects;

public class Rook implements ChessPiece {

    private final int position;
    private final int numberOfSteps;
    private final long stepsPosition;

    private final int COLUMN_SIZE = 8;
    private final int ROW_SIZE = 8;

    private long calculateMovesPosition(int position) {
        int columnNumber = position % COLUMN_SIZE;
        int rowNumber = position / ROW_SIZE;
        long positionMap = 1L << position;
        long result = 0;
        for (int i = 0; i < columnNumber; i++) {
            result |= BitOps.rSh(positionMap, i + 1);
        }
        for (int i = 0; i < COLUMN_SIZE - columnNumber - 1; i++) {
            result |= BitOps.lSh(positionMap, i + 1);
        }
        for (int i = 0; i < rowNumber; i++) {
            result |= BitOps.rSh(positionMap, (i + 1) * 8);
        }
        for (int i = 0; i < ROW_SIZE - rowNumber - 1; i++) {
            result |= BitOps.lSh(positionMap, (i + 1) * 8);
        }
        return result;
    }

    public Rook(int position, IStepsCalculator calculator) {
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
