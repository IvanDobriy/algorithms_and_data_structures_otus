package ru.otus.danilchenko.algorithms.lesson14.performance.set;

import java.util.Objects;

public class Vertex<T> {
    private Integer index;
    private T value;
    public Vertex(Integer index, T value){
        Objects.requireNonNull(value);
        this.index = index;
        this.value = value;
    }

    public int getIndex() {
        return index;
    }

    public T getValue() {
        return value;
    }
}
