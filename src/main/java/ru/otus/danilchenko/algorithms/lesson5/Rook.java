package ru.otus.danilchenko.algorithms.lesson5;

import java.util.Objects;

public class Rook implements ChessPiece {

    private final int position;
    private final int numberOfSteps;
    private final long stepsPosition;


    private long calculateMovesPosition(int position) {
        long positionMap = 1L << position;
        return 0;
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
