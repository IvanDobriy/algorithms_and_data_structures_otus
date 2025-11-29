package ru.otus.danilchenko.algorithms.lesson5;

public class King implements ChessPiece {
    private final long position;
    private final int numberOfMovies;
    private final long movesPosition;

    private long calculateMovesPosition(long position) {
        return 0;
    }

    private int calculateNumberOfMovies(long movesPosition) {
        return 0;
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
