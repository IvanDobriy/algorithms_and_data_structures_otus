package ru.otus.danilchenko.algorithms.lesson14.performance.set;

import java.util.Objects;

public class Edge<T> {
    private int index;
    private Vertex<T> first;
    private Vertex<T> second;
    private int weight;

    public Edge(int index, Vertex<T> first, Vertex<T> second) {
        Objects.requireNonNull(first);
        Objects.requireNonNull(second);
        this.first = first;
        this.second = second;
        this.index = index;
        this.weight = 0;
    }

    public Edge(int index, Vertex<T> first, Vertex<T> second, int weight) {
        this(index, first, second);
        this.weight = weight;
    }

    public Vertex<T> getFirst() {
        return first;
    }

    public Vertex<T> getSecond() {
        return second;
    }

    public int getWeight() {
        return weight;
    }

    public int getIndex() {
        return index;
    }
}
