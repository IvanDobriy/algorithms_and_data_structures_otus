package ru.otus.danilchenko.algorithms.lesson17.search;

import ru.otus.danilchenko.algorithms.lesson16.sceleton.Edge;
import ru.otus.danilchenko.algorithms.lesson4.dynamic_arrays.IArray;

public class Utils {
    static public void reverse(IArray<Edge> arr) {
        int left = 0;
        int right = arr.size() - 1;
        Edge tmp;
        while (left < right) {
            tmp = arr.get(left);
            arr.set(arr.get(right), left);
            arr.set(tmp, right);
            left++;
            right--;
        }
    }
}
