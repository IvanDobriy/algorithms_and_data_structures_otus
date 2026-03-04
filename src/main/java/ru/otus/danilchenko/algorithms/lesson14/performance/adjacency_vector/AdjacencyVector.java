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

}
