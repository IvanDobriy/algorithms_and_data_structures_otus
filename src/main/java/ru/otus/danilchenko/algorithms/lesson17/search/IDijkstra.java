package ru.otus.danilchenko.algorithms.lesson17.search;

import ru.otus.danilchenko.algorithms.lesson16.sceleton.Edge;
import ru.otus.danilchenko.algorithms.lesson4.dynamic_arrays.IArray;

public interface IDijkstra {
    IArray<Edge> execute(int from, int to);
}
