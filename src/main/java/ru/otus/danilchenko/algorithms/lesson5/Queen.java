package ru.otus.danilchenko.algorithms.lesson5;

import java.util.Objects;

public class Queen implements ChessPiece {

    private final int position;
    private final int numberOfSteps;
    private final long stepsPosition;

    private final ChessPiece[] elements;


    private long calculateStepsPosition() {
        long stepsPosition = 0;
        for (int i = 0; i < elements.length; i++) {
            stepsPosition |= elements[i].getStepsPosition();
        }
        return stepsPosition;
    }

    public Queen(int position, IStepsCalculator calculator) {
        Objects.requireNonNull(calculator);
        if (position < 0 || position > 63) {
            throw new IllegalArgumentException("position must be in range 0..63");
        }

        elements = new ChessPiece[]{new King(position, calculator), new Bishop(position, calculator), new Rook(position, calculator)};

        this.position = position;
        stepsPosition = calculateStepsPosition();
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
