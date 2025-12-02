package ru.otus.danilchenko.algorithms.lesson5;

import java.util.Objects;

public class Knight implements ChessPiece {
    final long nAB = 0xfcfcfcfcfcfcfcfcL;
    final long nGH = 0x3f3f3f3f3f3f3f3fL;

    private final long position;
    private final int numberOfSteps;
    private final long stepsPosition;


    private long calculateMovesPosition(long position) {
        long positionMap = 1L << position;
        if ((positionMap & (nAB & nGH)) != 0) {
            return BitOps.lSh(positionMap, 6)
                    | BitOps.rSh(positionMap, 6)
                    | BitOps.lSh(positionMap, 10)
                    | BitOps.rSh(positionMap, 10)
                    | BitOps.lSh(positionMap, 15)
                    | BitOps.rSh(positionMap, 15)
                    | BitOps.lSh(positionMap, 17)
                    | BitOps.rSh(positionMap, 17);
        }
        long result = nAB & BitOps.lSh(positionMap, 10)
                | nGH & BitOps.rSh(positionMap, 10)
                | nGH & BitOps.lSh(positionMap, 6)
                | nAB & BitOps.rSh(positionMap, 6);

        if ((positionMap & ~nAB) != 0) {
            result |= BitOps.lSh(positionMap, 17)
                    | nGH & BitOps.rSh(positionMap, 17)
                    | nGH & BitOps.lSh(positionMap, 15)
                    | nGH & BitOps.rSh(positionMap, 15);
        } else {
            result |= BitOps.rSh(positionMap, 17)
                    | BitOps.lSh(positionMap, 15)
                    | nAB & BitOps.rSh(positionMap, 15);
        }

        return result;
    }


    public Knight(long position, IStepsCalculator calculator) {
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
    public long getPosition() {
        return position;
    }

    @Override
    public long getStepsPosition() {
        return stepsPosition;
    }
}
