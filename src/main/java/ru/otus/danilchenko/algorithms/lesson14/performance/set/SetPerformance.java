package ru.otus.danilchenko.algorithms.lesson14.performance.set;

import ru.otus.danilchenko.algorithms.lesson4.dynamic_arrays.IArray;
import ru.otus.danilchenko.algorithms.lesson4.dynamic_arrays.SingleArray;

import java.util.Objects;

public class SetPerformance<T> {
    private IArray<Vertex<T>> vertexes;
    private IArray<Edge<T>> edges;

    public SetPerformance(int vertexSize, int edgeSize) {
        this.vertexes = new SingleArray<>(vertexSize);
        this.edges = new SingleArray<>(edgeSize);
    }

    public void setVertex(Vertex<T> vertex) {
        Objects.requireNonNull(vertex);
        final int index = vertex.getIndex();
        if (index < 0 || index > vertexes.size()) {
            throw new IllegalArgumentException(String.format("illegal vertex.index value: %d", index));
        }
        vertexes.set(vertex, vertex.getIndex());
    }

    public Vertex<T> getVertex(int index) {
        if (index < 0 || index > vertexes.size()) {
            throw new IllegalArgumentException(String.format("illegal index value: %d", index));
        }
        return vertexes.get(index);
    }

    public void setEdge(Edge<T> edge) {
        Objects.requireNonNull(edge);
        final int index = edge.getIndex();
        if (index < 0 || index > edges.size()) {
            throw new IllegalArgumentException(String.format("illegal edge.index value: %d", index));
        }
        edges.set(edge, edge.getIndex());
    }

    public Edge<T> getEdge(int index) {
        if (index < 0 || index > edges.size()) {
            throw new IllegalArgumentException(String.format("illegal edge.index value: %d", index));
        }
        return edges.get(index);
    }

    public int vertexesSize() {
        return vertexes.size();
    }

    public int edgesSize() {
        return edges.size();
    }
}
