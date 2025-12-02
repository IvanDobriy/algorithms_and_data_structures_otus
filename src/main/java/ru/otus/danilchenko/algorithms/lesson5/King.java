package ru.otus.danilchenko.algorithms.lesson5;

public class King implements ChessPiece {
    private final long position;
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

    private int calculateNumberOfMovies(long movesPosition) {
        int result = 0;
        while (movesPosition != 0) {
            movesPosition &= (movesPosition - 1);
            result++;
        }
        return result;
    }


    public King(long position) {
        this.position = position;
        stepsPosition = calculateMovesPosition(position);
        numberOfSteps = calculateNumberOfMovies(stepsPosition);
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
