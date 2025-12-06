package ru.otus.danilchenko.algorithms.lesson6;

public class Utils {
    public <T> void swap(T[] arr, int pos1, int pos2) {
        T tmp = arr[pos1];
        arr[pos1] = arr[pos2];
        arr[pos2] = tmp;
    }

    public int compare(int first, int second) {
        return first - second;
    }
}
