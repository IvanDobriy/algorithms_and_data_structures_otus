package ru.otus.danilchenko.algorithms.lesson14.performance.list;

import ru.otus.danilchenko.algorithms.lesson14.performance.set.Edge;
import ru.otus.danilchenko.algorithms.lesson4.dynamic_arrays.IArray;
import ru.otus.danilchenko.algorithms.lesson4.dynamic_arrays.SingleArray;

import java.util.Objects;

public class ListOfEdges<T> {
    private IArray<Edge<T>> edges;

    public ListOfEdges(int size) {
        edges = new SingleArray<>(size);
    }

    public void set(Edge<T> edge) {
        Objects.requireNonNull(edge);
        int index = edge.getIndex();
        if (index < 0 || index > edges.size()) {
            throw new IllegalArgumentException(String.format("illegal edge  index value: %d"));
        }
        edges.set(edge, index);
    }

    public Edge<T> get(int index) {
        if (index < 0 || index > edges.size()) {
            throw new IllegalArgumentException(String.format("illegal edge  index value: %d"));
        }
        return edges.get(index);
    }

    public int size() {
        return edges.size();
    }
}
