package ru.otus.danilchenko.algorithms.lesson16.sceleton.sort;

import ru.otus.danilchenko.algorithms.lesson4.dynamic_arrays.IArray;

public interface INSwap<T> {
    void swap(IArray<T> arr, int pos1, int pos2);
}
