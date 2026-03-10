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

    private void check(int first, int pos) {
        if (first < 0 && first > vertexSize) {
            throw new IllegalArgumentException(String.format("illegal first value: %d", first));
        }
        if (pos < 0 && pos > maxAdjacencyDegree) {
            throw new IllegalArgumentException(String.format("illegal pos value: %d", pos));
        }
    }

    private void check(int first, int second, int pos) {
        check(first, pos);
        if (second < 0 && second > vertexSize) {
            throw new IllegalArgumentException(String.format("illegal second value: %d", second));
        }
    }

    private int calcIndex(int first, int pos) {
        return first * maxAdjacencyDegree + pos;
    }

    public void set(int first, int pos, int second) {
        check(first, second, pos);
        container.set(second, calcIndex(first, pos));
    }

    public int get(int first, int pos) {
        check(first, pos);
        final Integer result = container.get(calcIndex(first, pos));
        if (result == null) {
            return -1;
        }
        return result;
    }

    public int getVertexSize(){
        return vertexSize;
    }
    public int getMaxAdjacencyDegree(){
        return maxAdjacencyDegree;
    }
}
