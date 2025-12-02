package ru.otus.danilchenko.algorithms.lesson5;

public class BitOps {
    public static long lSh(long value, int shiftPosition) {
        if(shiftPosition < 0){
            throw new IllegalArgumentException("shift position must be positive");
        }
        return value << shiftPosition;
    }

    public static long rSh(long value, int shiftPosition) {
        if(shiftPosition < 0){
            throw new IllegalArgumentException("shift position must be positive");
        }
        if(shiftPosition == 0){
            return value;
        }
        if (value < 0) {
            return (value >> shiftPosition) ^ (0x8000000000000000L >> (shiftPosition - 1));
        }
        return value >> shiftPosition;
    }
}
