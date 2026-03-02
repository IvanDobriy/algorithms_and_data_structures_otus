package ru.otus.danilchenko.algorithms.lesson14;

import ru.otus.danilchenko.algorithms.lesson4.dynamic_arrays.IArray;

import java.util.Objects;

public class SetPerformance<T> {
    private IArray<Vertex<T>> vertex;
    private IArray<Edge<T>> edge;

    public SetPerformance(IArray<Vertex<T>> vertex, IArray<Edge<T>> edge) {
        Objects.requireNonNull(vertex);
        Objects.requireNonNull(edge);
        this.vertex = vertex;
        this.edge = edge;
    }

    public IArray<Vertex<T>> getVertex() {
        return vertex;
    }

    public IArray<Edge<T>> getEdge() {
        return edge;
    }
}
