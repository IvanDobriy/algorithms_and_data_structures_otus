package ru.otus.danilchenko.algorithms.lesson5;

public class Knight implements ChessPiece {
    final long nAB = 0xfcfcfcfcfcfcfcfcL;
    final long nGH = 0x3f3f3f3f3f3f3f3fL;

    private final long position;
    private final int numberOfMovies;
    private final long movesPosition;


    private long calculateMovesPosition(long position) {
        position = 1L << position;
        if (position > 0) {
            if ((position & (nAB & nGH)) != 0) {
                return position << 6 | position >> 6 | position << 10 | position >> 10 | position << 15 | position >> 15 | position << 17 | position >> 17;
            }
            long result = nAB & position << 10
                    | nGH & position >> 10
                    | nGH & position << 6
                    | nAB & position >> 6;

            if ((position & ~nAB) != 0) {
                result |= position << 17
                        | nGH & position >> 17
                        | nGH & position << 15
                        | nGH & position >> 15;
            } else {
                result |= position >> 17
                        | position << 15
                        | nAB & position >> 15;
            }
            return result;
        }
        if ((position & (nAB & nGH)) != 0) {
            return position << 6 | (position >> 6 ^ position >> 5) | position << 10 | (position >> 10 ^ position >> 9) | position << 15 | (position >> 15 ^ position >> 14) | position << 17 | (position >> 17 ^ position >> 16);
        }
        long result = nAB & position << 10
                | nGH & (position >> 10 ^ position >> 9)
                | nGH & (position << 6)
                | nAB & (position >> 6 ^ position >> 5);

        if ((position & ~nAB) != 0) {
            result |= position << 17
                    | nGH & (position >> 17 ^ position >> 16)
                    | nGH & (position << 15)
                    | nGH & (position >> 15 ^ position >> 14);
        } else {
            result |= (position >> 17 ^ position >> 16)
                    | position << 15
                    | nAB & (position >> 15 ^ position >> 14);
        }
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
        return position;
    }

    @Override
    public long getMovesPosition() {
        return movesPosition;
    }
}
