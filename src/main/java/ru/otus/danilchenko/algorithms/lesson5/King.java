package ru.otus.danilchenko.algorithms.lesson5;

public class King implements ChessPiece {
    private final long position;
    private final int numberOfMovies;
    private final long movesPosition;

    private final long nA = 0xfefefefefefefefeL;
    private final long nF = 0x7f7f7f7f7f7f7f7fL;

    private long calculateMovesPosition(long position) {
        position = 1L << position;
        if (position > 0) {
            return nA & (position << 1)
                    | nF & (position >> 1)
                    | position << 8
                    | position >> 8
                    | nA & (position << 9)
                    | nF & (position >> 9)
                    | nF & (position << 7)
                    | nA & (position >> 7);
        }

        return nA & (position << 1)
                | nF & (position >> 1 ^ position)
                | position << 8
                | position >> 8 ^ (position >> 7)
                | nA & (position << 9)
                | nF & (position >> 9 ^ (position >> 8))
                | nF & (position << 7)
                | nA & (position >> 7 ^ (position >> 6));
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
