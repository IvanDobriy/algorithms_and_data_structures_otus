package ru.otus.danilchenko.algorithms.lesson16.sceleton;

import ru.otus.danilchenko.algorithms.lesson14.performance.adjacency_vector.AdjacencyVector;
import ru.otus.danilchenko.algorithms.lesson16.sceleton.sort.INSort;
import ru.otus.danilchenko.algorithms.lesson16.sceleton.sort.NQuickSort;
import ru.otus.danilchenko.algorithms.lesson16.sceleton.sort.NUtils;
import ru.otus.danilchenko.algorithms.lesson4.dynamic_arrays.IArray;
import ru.otus.danilchenko.algorithms.lesson4.dynamic_arrays.SingleArray;
import ru.otus.danilchenko.algorithms.sort.IComparator;

import java.util.Objects;

public class KrascalAdjacencyVector implements IKrascal {
    private final AdjacencyVector adjacencyVector;
    private final IComparator<Edge> comparator;
    private Integer weight;
    private IArray<Edge> result;
    private INSort<Edge> sorting;

    public KrascalAdjacencyVector(AdjacencyVector adjacencyVector) {
        Objects.requireNonNull(adjacencyVector);
        this.adjacencyVector = adjacencyVector;
        this.comparator = (Edge e1, Edge e2) -> {
            return e1.getWeight() - e2.getWeight();
        };
        this.weight = 0;
        this.result = null;
        final var utils = new NUtils();
        this.sorting = new NQuickSort<>(comparator, utils::swap);
    }

    private IArray<Edge> getSortedEdges() {
        int vertexSize = adjacencyVector.getVertexSize();
        IArray<Edge> result = new SingleArray<>(0);
        int vertex;
        for (int i = 0; i < vertexSize; i++) {
            for (int j = 0; j < adjacencyVector.getMaxAdjacencyDegree(); j++) {
                vertex = adjacencyVector.get(i, j);
                if (vertex <= i) {
                    continue;
                }
                result.add(new Edge(i, vertex, adjacencyVector.getWeight(i, j)), result.size());
            }
        }
        return sorting.sort(result);
    }

    @Override
    public IArray<Edge> execute() {
        if (result != null) {
            return result;
        }
        IArray<Edge> sortedEdges = getSortedEdges();
        
        return null;
    }
}
