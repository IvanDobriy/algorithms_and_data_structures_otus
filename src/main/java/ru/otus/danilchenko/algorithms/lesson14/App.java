package ru.otus.danilchenko.algorithms.lesson14;

import ru.otus.danilchenko.algorithms.lesson14.performance.adjacency.AdjacencyMatrix;
import ru.otus.danilchenko.algorithms.lesson14.performance.incidence.IncidenceMatrix;
import ru.otus.danilchenko.algorithms.lesson14.performance.set.Edge;
import ru.otus.danilchenko.algorithms.lesson14.performance.set.SetPerformance;
import ru.otus.danilchenko.algorithms.lesson14.performance.set.Vertex;
import ru.otus.danilchenko.algorithms.lesson4.dynamic_arrays.SingleArray;

public class App {
    private void setPerformance() {
        final var vertexs = new SingleArray<Vertex<Integer>>(0);
        for (int i = 0; i < 7; i++) {
            vertexs.add(new Vertex<>(i, i + 1), vertexs.size());
        }
        int index = 0;
        final var edges = new SingleArray<Edge<Integer>>(0);
        edges.add(new Edge<>(vertexs.get(0), vertexs.get(3)), index++);
        edges.add(new Edge<>(vertexs.get(1), vertexs.get(3)), index++);
        edges.add(new Edge<>(vertexs.get(1), vertexs.get(5)), index++);
        edges.add(new Edge<>(vertexs.get(2), vertexs.get(4)), index++);
        edges.add(new Edge<>(vertexs.get(2), vertexs.get(6)), index++);

        final var setPerformance = new SetPerformance<Integer>(vertexs, edges);
    }

    private void adjacencyMatrix() {
        final var adjacency = new AdjacencyMatrix(7);
        adjacency.set(0, 3);
        adjacency.set(1, 3);
        adjacency.set(1, 5);
        adjacency.set(2, 4);
        adjacency.set(2, 6);
        var a = 1;
    }


    private void incidenceMatrix() {
        final var incidence = new IncidenceMatrix(7, 5);
        incidence.set(0, 0, 1);
        incidence.set(1, 1, 1);
        incidence.set(1, 2, 1);
        incidence.set(2, 3, 1);
        incidence.set(2, 4, 1);
        var a = 1;
    }

    public static void main(String[] args) {
        var app = new App();
        app.incidenceMatrix();
    }
}
