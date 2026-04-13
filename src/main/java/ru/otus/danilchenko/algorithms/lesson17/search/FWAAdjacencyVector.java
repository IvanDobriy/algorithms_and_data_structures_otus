package ru.otus.danilchenko.algorithms.lesson17.search;

import ru.otus.danilchenko.algorithms.lesson14.performance.adjacency_vector.AdjacencyVector;
import ru.otus.danilchenko.algorithms.lesson16.sceleton.Edge;
import ru.otus.danilchenko.algorithms.lesson4.dynamic_arrays.IArray;
import ru.otus.danilchenko.algorithms.lesson4.dynamic_arrays.SingleArray;

import java.util.Objects;

public class FWAAdjacencyVector implements IFWA {

    private IArray<Way> ways;
    private final AdjacencyVector adjacencyVector;
    private final int max;

    private class Way {
        int from;
        int weight;

        Way(int from, int weight) {
            this.from = from;
            this.weight = weight;
        }
    }

    private int calcIndex(int x, int y) {
        return x * adjacencyVector.getVertexSize() + y;
    }

    private int getMax() {
        int result = 0;
        for (int i = 0; i < adjacencyVector.getVertexSize(); i++) {
            for (int j = 0; j < adjacencyVector.getMaxAdjacencyDegree(); j++) {
                result += adjacencyVector.getWeight(i, j);
            }
        }
        return result;
    }

    private void fillWays() {
        for (int i = 0; i < adjacencyVector.getVertexSize(); i++) {
            ways.set(new Way(i, 0), calcIndex(i, i));
            for (int j = 0; j < adjacencyVector.getMaxAdjacencyDegree(); j++) {
                int av = adjacencyVector.get(i, j);
                if (av < 0) {
                    continue;
                }
                int w = adjacencyVector.getWeight(i, j);
                ways.set(new Way(i, w), calcIndex(i, av));
            }
        }
    }

    public FWAAdjacencyVector(AdjacencyVector adjacencyVector) {
        Objects.requireNonNull(adjacencyVector);
        this.adjacencyVector = adjacencyVector;
        ways = null;
        max = getMax();
        ways = new SingleArray<>(adjacencyVector.getVertexSize() * adjacencyVector.getVertexSize());
        fillWays();
    }

    private Way getWay(int x, int y) {
        Way way = ways.get(calcIndex(x, y));
        if (way != null) {
            return way;
        }
        return new Way(x, max);
    }

    private void setWay(int x, int y, Way way) {
        ways.set(way, calcIndex(x, y));
    }

    private IArray<Edge> calcPath(int from, int to) {
        IArray<Edge> result = new SingleArray<>(0);
        int pos = to;
        Way w;
        do {
            w = getWay(from, pos);
            if (w.from == pos) {
                return null;
            }
            result.add(new Edge(w.from, pos, 0), result.size());
            pos = w.from;
        } while (w.from != from);
        Utils.reverse(result);
        return result;
    }

    private void check(int from, int to) {
        if (from < 0) {
            throw new IllegalArgumentException("from < 0");
        }
        if (to < 0) {
            throw new IllegalArgumentException("to < 0");
        }
        if (from > adjacencyVector.getVertexSize()) {
            throw new IllegalArgumentException("from > vertex size");
        }
        if (to > adjacencyVector.getVertexSize()) {
            throw new IllegalArgumentException("to > vertex size");
        }
    }

    @Override
    public IArray<Edge> execute(int from, int to) {
        check(from, to);
        for (int k = 0; k < adjacencyVector.getVertexSize(); k++) {
            for (int i = 0; i < adjacencyVector.getVertexSize(); i++) {
                if (i == k) {
                    continue;
                }
                for (int j = 0; j < adjacencyVector.getVertexSize(); j++) {
                    if (k == j) {
                        continue;
                    }
                    if (i == j) {
                        continue;
                    }
                    int middleWeight = getWay(i, k).weight + getWay(k, j).weight;

                    if (middleWeight < getWay(i, j).weight) {
                        setWay(i, j, new Way(k, middleWeight));
                    }
                }
            }
        }
        return calcPath(from, to);
    }
}
