package ru.otus.danilchenko.algorithms.lesson5;

public class Knight implements ChessPiece {
    final long nAB = 0x3f3f3f3f3f3f3f3fL;
    final long nEF = 0xfcfcfcfcfcfcfcfcL;

    private final long position;
    private final int numberOfMovies;
    private final long movesPosition;


    private long calculateMovesPosition(long position) {
        position = 1L << position;
        long result =
                nEF & (position << 10)
                        | nAB & (position >> 10)
                        | position << 17
                        | nAB & (position >> 17)
                        | nAB & (position << 15)
                        | nEF & (position >> 15)
                        | nEF & (position << 6)
                        | nAB & (position >> 7);
        return result;
    }

    private int calculateNumberOfMovies(long movesPosition) {
        int result = 0;
        while (movesPosition != 0) {
            movesPosition &= (movesPosition - 1);
            result++;
        }
        return result;
    }

    public Knight(long position) {
        this.position = position;
        movesPosition = calculateMovesPosition(position);
        numberOfMovies = calculateNumberOfMovies(movesPosition);
    }

    @Override
    public int getNumberOfMoves() {
        return numberOfMovies;
    }

    @Override
    public long getPosition() {
        return movesPosition;
    }

    @Override
    public long getMovesPosition() {
        return numberOfMovies;
    }
}
