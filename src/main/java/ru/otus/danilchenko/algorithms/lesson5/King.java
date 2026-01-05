package ru.otus.danilchenko.algorithms.lesson5;

import java.util.Objects;

public class King implements ChessPiece {
    private final int position;
    private final int numberOfSteps;
    private final long stepsPosition;

    private final long nA = 0xfefefefefefefefeL;
    private final long nH = 0x7f7f7f7f7f7f7f7fL;

    private long calculateMovesPosition(long position) {
        long positionMap = 1L << position;
        return nA & BitOps.lSh(positionMap, 1)
                | nH & (BitOps.rSh(positionMap, 1))
                | BitOps.lSh(positionMap, 8)
                | BitOps.rSh(positionMap, 8)
                | nA & (BitOps.lSh(positionMap, 9))
                | nH & (BitOps.rSh(positionMap, 9))
                | nH & (BitOps.lSh(positionMap, 7))
                | nA & (BitOps.rSh(positionMap, 7));
    }


    public King(int position, IStepsCalculator calculator) {
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
