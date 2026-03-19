package ru.otus.danilchenko.algorithms.lesson16.sceleton.sort;

import ru.otus.danilchenko.algorithms.lesson4.dynamic_arrays.IArray;

public class NUtils {
    public <T> void swap(IArray<T> arr, int pos1, int pos2) {
        T tmp = arr.get(pos1);
        arr.set(arr.get(pos2), pos1);
        arr.set(tmp, pos2);
    }
}
