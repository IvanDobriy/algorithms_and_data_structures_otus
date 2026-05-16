package ru.otus.danilchenko.algorithms.lesson16.sceleton;

import ru.otus.danilchenko.algorithms.lesson14.performance.adjacency_vector.AdjacencyVector;
import ru.otus.danilchenko.algorithms.lesson4.dynamic_arrays.IArray;
import ru.otus.danilchenko.algorithms.lesson4.dynamic_arrays.SingleArray;

import java.util.Objects;

public class PrimAdjacencyVector implements IPrim {
    private final AdjacencyVector adjacencyVector;
    private IArray<Edge> result;
    private int weight;
    private int max;
    private boolean[] isHandled;

    public PrimAdjacencyVector(AdjacencyVector adjacencyVector) {
        Objects.requireNonNull(adjacencyVector);
        this.adjacencyVector = adjacencyVector;
        weight = 0;
        result = null;
        isHandled = new boolean[adjacencyVector.getVertexSize()];
    }

    private int countMax() {
        int result = 0;
        for (int i = 0; i < adjacencyVector.getVertexSize(); i++) {
            for (int j = 0; j < adjacencyVector.getMaxAdjacencyDegree(); j++) {
                var v = adjacencyVector.get(i, j);
                if (v <= i) {
                    continue;
                }
                var w = adjacencyVector.getWeight(i, j);
                result += w;
            }
        }
        return result;
    }

    private Edge nextEdge() {
        int min = max;
        int begin = -1;
        int end = -1;
        Edge result = null;
        for (int i = 0; i < adjacencyVector.getVertexSize(); i++) {
            for (int j = 0; j < adjacencyVector.getMaxAdjacencyDegree(); j++) {
                var v = adjacencyVector.get(i, j);
                if (v < 0) {
                    continue;
                }
                if (isHandled[v]) {
                    continue;
                }
                var w = adjacencyVector.getWeight(i, j);
                if (min <= w) {
                    continue;
                }
                min = w;
                begin = i;
                end = v;
            }
        }
        if (begin >= 0) {
            result = new Edge(begin, end, min);
        }
        return result;
    }

    @Override
    public IArray<Edge> execute() {
        if (result != null) {
            return result;
        }
        weight = 0;
        final IArray<Edge> r = new SingleArray<>(0);
        isHandled[0] = true;
        max = countMax();
        for (int i = 0; i < adjacencyVector.getVertexSize(); i++) {
            Edge e = nextEdge();
            if (e == null) {
                break;
            }
            weight += e.getWeight();
            r.add(e, r.size());
            isHandled[e.getSecond()] = true;
        }
        result = r;
        return result;
    }
}
