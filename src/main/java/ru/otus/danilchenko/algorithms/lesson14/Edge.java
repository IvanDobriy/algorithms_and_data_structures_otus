package ru.otus.danilchenko.algorithms.lesson14;

import java.util.Objects;

public class Edge<T> {
    private Vertex<T> first;
    private Vertex<T> second;

    public Edge(Vertex<T> first, Vertex<T> second) {
        Objects.requireNonNull(first);
        Objects.requireNonNull(second);
        this.first = first;
        this.second = second;
    }

    public Vertex<T> getFirst() {
        return first;
    }

    public Vertex<T> getSecond() {
        return second;
    }
}
