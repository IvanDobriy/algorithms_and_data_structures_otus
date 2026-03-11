package ru.otus.danilchenko.algorithms.lesson15.topological_sorting;

import ru.otus.danilchenko.algorithms.lesson14.performance.set.Vertex;
import ru.otus.danilchenko.algorithms.lesson4.dynamic_arrays.IArray;

public interface IDFS {
    IArray<Integer> execute(int begin, int end);
}
