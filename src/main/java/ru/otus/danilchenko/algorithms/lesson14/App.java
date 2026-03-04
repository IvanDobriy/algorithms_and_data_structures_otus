package ru.otus.danilchenko.algorithms.lesson14;

import ru.otus.danilchenko.algorithms.lesson14.performance.adjacency.AdjacencyMatrix;
import ru.otus.danilchenko.algorithms.lesson14.performance.incidence.IncidenceMatrix;
import ru.otus.danilchenko.algorithms.lesson14.performance.list.ListOfEdges;
import ru.otus.danilchenko.algorithms.lesson14.performance.set.Edge;
import ru.otus.danilchenko.algorithms.lesson14.performance.set.SetPerformance;
import ru.otus.danilchenko.algorithms.lesson14.performance.set.Vertex;
import ru.otus.danilchenko.algorithms.lesson4.dynamic_arrays.IArray;
import ru.otus.danilchenko.algorithms.lesson4.dynamic_arrays.SingleArray;

public class App {
    private void setPerformance() {
        final var setPerformance = new SetPerformance<Integer>(7, 5);
        for (int i = 0; i < 7; i++) {
            setPerformance.setVertex(new Vertex<>(i, i + 1));
        }
        int index = 0;
        setPerformance.setEdge(new Edge<>(index++, setPerformance.getVertex(0), setPerformance.getVertex(3)));
        setPerformance.setEdge(new Edge<>(index++, setPerformance.getVertex(1), setPerformance.getVertex(3)));
        setPerformance.setEdge(new Edge<>(index++, setPerformance.getVertex(1), setPerformance.getVertex(5)));
        setPerformance.setEdge(new Edge<>(index++, setPerformance.getVertex(2), setPerformance.getVertex(4)));
        setPerformance.setEdge(new Edge<>(index++, setPerformance.getVertex(2), setPerformance.getVertex(6)));
        var a = 1;
    }

    private void adjacencyMatrix() {
        final var adjacency = new AdjacencyMatrix(7, false);
        adjacency.set(0, 3);
        adjacency.set(1, 3);
        adjacency.set(1, 5);
        adjacency.set(2, 4);
        adjacency.set(2, 6);
        var a = 1;
    }


    private void incidenceMatrix() {
        final var incidence = new IncidenceMatrix(7, 5, false);
        incidence.set(0, 0, 1);
        incidence.set(1, 1, 1);
        incidence.set(1, 2, 1);
        incidence.set(2, 3, 1);
        incidence.set(2, 4, 1);
        var a = 1;
    }

    private void listOfEdges() {
        IArray<Vertex<Integer>> vertexes = new SingleArray<>(7);
        for(int i  = 0; i < vertexes.size(); i++){
            vertexes.set(new Vertex<>(i, i+1), i);
        }
        final var edges = new ListOfEdges<Integer>(5);
        edges.set(new Edge<>(0, vertexes.get(0), vertexes.get(3)));
        edges.set(new Edge<>(1, vertexes.get(1), vertexes.get(3)));
        edges.set(new Edge<>(2, vertexes.get(1), vertexes.get(5)));
        edges.set(new Edge<>(3, vertexes.get(2), vertexes.get(4)));
        edges.set(new Edge<>(4, vertexes.get(2), vertexes.get(6)));
        var a = 2;
    }

    public static void main(String[] args) {
        var app = new App();
        app.listOfEdges();
    }
}
