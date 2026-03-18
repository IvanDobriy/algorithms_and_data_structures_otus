package ru.otus.danilchenko.algorithms.lesson16.sceleton;

import ru.otus.danilchenko.algorithms.lesson14.performance.set.Edge;
import ru.otus.danilchenko.algorithms.lesson4.dynamic_arrays.IArray;

public interface IKrascal<T> {
        IArray<Edge<T>> execute();
}
