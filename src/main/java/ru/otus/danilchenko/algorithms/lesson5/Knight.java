package ru.otus.danilchenko.algorithms.lesson5;

public class Knight implements ChessPiece {
    final long nAB = 0xfcfcfcfcfcfcfcfcL;
    final long nGH = 0x3f3f3f3f3f3f3f3fL;

    private final long position;
    private final int numberOfMovies;
    private final long movesPosition;


    private long calculateMovesPosition(long position) {
        position = 1L << position;

        if ((position & (nAB & nGH)) != 0) {
            return position << 6 | position >> 6 | position << 10 | position >> 10 | position << 15 | position >> 15 | position << 17 | position >> 17;
        }
        
        if ((position & ~nAB) != 0) {
            return nAB & (position << 10)
                    | nGH & (position >> 10)
                    | position << 17
                    | nGH & (position >> 17)
                    | nGH & (position << 6)
                    | nAB & (position >> 6)
                    | nGH & (position << 15)
                    | nGH & (position >> 15);
        }

        return nAB & (position << 10)
                | nGH & (position >> 10)
                | position >> 17
                | nGH & (position << 6)
                | nAB & (position >> 6)
                | position << 15
                | nAB & (position >> 15);
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
        return position;
    }

    @Override
    public long getMovesPosition() {
        return movesPosition;
    }
}
