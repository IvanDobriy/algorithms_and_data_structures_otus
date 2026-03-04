package ru.otus.danilchenko.algorithms.lesson14.performance.incidence;

import ru.otus.danilchenko.algorithms.lesson4.dynamic_arrays.IArray;
import ru.otus.danilchenko.algorithms.lesson4.dynamic_arrays.SingleArray;

public class IncidenceMatrix {
    private final IArray<Integer> container;
    private final boolean isOriented;
    private int edgeSize;
    private int vertexSize;


    private int calcIndex(int vertex, int edge) {
        return vertex * edgeSize + edge;
    }

    public IncidenceMatrix(int vertexSize, int edgeSize, boolean isOriented) {
        if (edgeSize < 0) {
            throw new IllegalArgumentException("edgeSize < 0");
        }
        if (vertexSize < 0) {
            throw new IllegalArgumentException("vertexSize < 0");
        }
        this.isOriented = isOriented;
        this.edgeSize = edgeSize;
        this.vertexSize = vertexSize;
        container = new SingleArray<>(edgeSize * vertexSize);
    }

    private void check(int vertex, int edge, int value) {
        check(vertex, edge);
        if (value < 0 && !isOriented) {
            throw new IllegalArgumentException(String.format("graph is unordered but value is negative:%d", value));
        }
    }

    private void check(int vertex, int edge) {
        if (vertex < 0 || vertex > vertexSize) {
            throw new IllegalArgumentException(String.format("illegal vertex: %d", vertex));
        }
        if (edge < 0 || edge > edgeSize) {
            throw new IllegalArgumentException(String.format("illegal edge: %d", edge));
        }
    }

    public void set(int vertex, int edge, int value) {
        check(vertex, edge, value);
        container.set(value, calcIndex(vertex, edge));
    }

    public int get(int vertex, int edge) {
        check(vertex, edge);
        final Integer result = container.get(calcIndex(vertex, edge));
        if (result == null) {
            return 0;
        }
        return result;
    }

    public boolean isOriented() {
        return isOriented;
    }
}

