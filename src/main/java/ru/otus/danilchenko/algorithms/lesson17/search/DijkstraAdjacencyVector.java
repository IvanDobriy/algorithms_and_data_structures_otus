package ru.otus.danilchenko.algorithms.lesson17.search;

import ru.otus.danilchenko.algorithms.lesson14.performance.adjacency_vector.AdjacencyVector;
import ru.otus.danilchenko.algorithms.lesson16.sceleton.Edge;
import ru.otus.danilchenko.algorithms.lesson4.dynamic_arrays.IArray;
import ru.otus.danilchenko.algorithms.lesson4.dynamic_arrays.SingleArray;
import ru.otus.danilchenko.algorithms.sort.IComparator;

import java.util.Objects;

public class DijkstraAdjacencyVector implements IDijkstra {
    private final AdjacencyVector adjacencyVector;
    private IArray<Way> ways;
    private IComparator<Way> wayIComparator;
    private final int max;

    private class Way {
        int from;
        int weight;

        Way(int from, int weight) {
            this.from = from;
            this.weight = weight;
        }
    }

    public DijkstraAdjacencyVector(AdjacencyVector adjacencyVector) {
        Objects.requireNonNull(adjacencyVector);
        this.adjacencyVector = adjacencyVector;
        ways = null;
        max = findMax();
        wayIComparator = (Way first, Way second) -> {
            return first.weight - second.weight;
        };
    }

    private void check(int from, int to) {

    }


    private int findMax() {
        int result = 0;
        for (int i = 0; i < adjacencyVector.getVertexSize(); i++) {
            for (int j = 0; j < adjacencyVector.getMaxAdjacencyDegree(); j++) {
                var v = adjacencyVector.get(i, j);
                if (v <= i) {
                    continue;
                }
                var w = adjacencyVector.get(i, j);
                result += w;
            }
        }
        return result;
    }

    private int getMinIndex(IArray<Way> ways, boolean[] visited) {
        int minIndex = -1;
        for (int i = 0; i < ways.size(); i++) {
            if (visited[i]) {
                continue;
            }
            if (minIndex < 0) {
                minIndex = i;
                continue;
            }
            if (wayIComparator.compare(ways.get(i), ways.get(minIndex)) < 0) {
                minIndex = i;
            }
        }
        return minIndex;
    }

    private IArray<Edge> calcPath(int from, int to) {
        IArray<Edge> r = new SingleArray<>(0);
        int pos = to;
        Way w;
        do {
            w = ways.get(pos);
            if (w.from == pos) {
                return null;
            }
            r.add(new Edge(w.from, pos, 0), r.size());
            pos = w.from;
        }while (w.from != from);
        return r;
    }


    @Override
    public IArray<Edge> execute(int from, int to) {
        check(from, to);
        if (this.ways != null) {
            return calcPath(from, to);
        }

        IArray<Way> ways = new SingleArray<>(adjacencyVector.getVertexSize());
        for (int i = 0; i < ways.size(); i++) {
            ways.set(new Way(i, max), i);
        }
        boolean[] visited = new boolean[adjacencyVector.getVertexSize()];
        int min = 0;
        ways.set(new Way(min, 0), min);
        for (int i = 0; i < adjacencyVector.getVertexSize(); i++) {
            min = getMinIndex(ways, visited);
            visited[min] = true;
            for (int j = 0; j < adjacencyVector.getMaxAdjacencyDegree(); j++) {
                int av = adjacencyVector.get(min, j);

                if (av < 0) {
                    continue;
                }

                if (visited[av]) {
                    continue;
                }

                int weight = ways.get(min).weight + adjacencyVector.getWeight(min, j);

                if (weight < ways.get(av).weight) {
                    ways.set(new Way(min, weight), av);
                }
            }
        }
        this.ways = ways;
        return calcPath(from, to);
    }
}
