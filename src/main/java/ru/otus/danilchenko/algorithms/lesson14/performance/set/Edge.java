package ru.otus.danilchenko.algorithms.lesson14.performance.set;

import java.util.Objects;

public class Edge<T> {
    private int index;
    private Vertex<T> first;
    private Vertex<T> second;

    public Edge(int index, Vertex<T> first, Vertex<T> second) {
        Objects.requireNonNull(first);
        Objects.requireNonNull(second);
        this.first = first;
        this.second = second;
        this.index = index;
    }

    public Vertex<T> getFirst() {
        return first;
    }

    public Vertex<T> getSecond() {
        return second;
    }

    public int getIndex() {
        return index;
    }
}
