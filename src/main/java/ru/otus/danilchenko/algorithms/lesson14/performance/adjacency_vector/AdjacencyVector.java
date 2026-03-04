package ru.otus.danilchenko.algorithms.lesson14.performance.adjacency_vector;

import ru.otus.danilchenko.algorithms.lesson4.dynamic_arrays.IArray;
import ru.otus.danilchenko.algorithms.lesson4.dynamic_arrays.SingleArray;

public class AdjacencyVector {
    private IArray<Integer> container;

    private final int vertexSize;
    private final int maxAdjacencyDegree;

    public AdjacencyVector(int vertexSize, int maxAdjacencyDegree) {
        this.vertexSize = vertexSize;
        this.maxAdjacencyDegree = maxAdjacencyDegree;
        container = new SingleArray<>(vertexSize * maxAdjacencyDegree);
    }

    private void check(int first, int degree) {
        if (first < 0 && first > vertexSize) {
            throw new IllegalArgumentException(String.format("illegal first value: %d", first));
        }
        if (degree < 0 && degree > maxAdjacencyDegree) {
            throw new IllegalArgumentException(String.format("illegal degree value: %d", degree));
        }
    }

    private void check(int first, int second, int degree) {
        check(first, degree);
        if (second < 0 && second > vertexSize) {
            throw new IllegalArgumentException(String.format("illegal second value: %d", second));
        }
    }

    private int calcIndex(int first, int degree) {
        return first * maxAdjacencyDegree + degree;
    }

    public void set(int first, int degree, int second) {
        check(first, second, degree);
        container.set(degree, calcIndex(first, degree));
    }

    public int get(int first, int degree) {
        check(first, degree);
        final Integer result = container.get(calcIndex(first, degree));
        if (result == null) {
            return 0;
        }
        return result;
    }
}
