package ru.otus.danilchenko.algorithms.lesson16.sceleton;

import ru.otus.danilchenko.algorithms.lesson14.performance.adjacency_vector.AdjacencyVector;
import ru.otus.danilchenko.algorithms.lesson16.sceleton.sort.INSort;
import ru.otus.danilchenko.algorithms.lesson16.sceleton.sort.NQuickSort;
import ru.otus.danilchenko.algorithms.lesson16.sceleton.sort.NUtils;
import ru.otus.danilchenko.algorithms.lesson4.dynamic_arrays.IArray;
import ru.otus.danilchenko.algorithms.lesson4.dynamic_arrays.SingleArray;
import ru.otus.danilchenko.algorithms.sort.IComparator;

import java.util.Objects;

public class KruscalAdjacencyVector implements IKruscal {
    private final AdjacencyVector adjacencyVector;
    private final IComparator<Edge> comparator;
    private Integer weight;
    private IArray<Edge> result;
    private INSort<Edge> sorting;

    public KruscalAdjacencyVector(AdjacencyVector adjacencyVector) {
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
        IArray<Edge> r = new SingleArray<>(0);
        IArray<Edge> sortedEdges = getSortedEdges();
        final UnionFind unionFind =  new UnionFind(adjacencyVector.getVertexSize());
        for(int i = 0; i < sortedEdges.size(); i++){
            Edge edge = sortedEdges.get(i);
            int first = unionFind.find(edge.getFirst());
            int second = unionFind.find(edge.getSecond());
            if(first == second){
                continue;
            }
            r.add(edge, r.size());
            weight += edge.getWeight();
            unionFind.union(first, second);
        }
        result = r;
        return result;
    }
}
