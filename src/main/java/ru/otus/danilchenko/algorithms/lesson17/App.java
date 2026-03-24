package ru.otus.danilchenko.algorithms.lesson17;

import ru.otus.danilchenko.algorithms.lesson14.performance.adjacency_vector.AdjacencyVector;
import ru.otus.danilchenko.algorithms.lesson17.search.DijkstraAdjacencyVector;
import ru.otus.danilchenko.algorithms.lesson17.search.IDijkstra;

public class App {

    private final AdjacencyVector adjacencyVector;

    App() {
        adjacencyVector = new AdjacencyVector(6, 4);
        adjacencyVector.set(0, 0, 1, 7);
        adjacencyVector.set(0, 1, 2, 9);
        adjacencyVector.set(0, 2, 5, 14);

        adjacencyVector.set(1, 0, 0, 7);
        adjacencyVector.set(1, 1, 2, 10);
        adjacencyVector.set(1, 2, 3, 15);

        adjacencyVector.set(2, 0, 0, 9);
        adjacencyVector.set(2, 1, 1, 10);
        adjacencyVector.set(2, 2, 3, 11);
        adjacencyVector.set(2, 3, 5, 2);

        adjacencyVector.set(3, 0, 1, 15);
        adjacencyVector.set(3, 1, 2, 11);
        adjacencyVector.set(3, 2, 4, 6);

        adjacencyVector.set(4, 0, 3, 6);
        adjacencyVector.set(4, 1, 5, 9);

        adjacencyVector.set(5, 0, 0, 14);
        adjacencyVector.set(5, 1, 2, 2);
        adjacencyVector.set(5, 2, 4, 9);
    }

    private void dijkstra() {
        IDijkstra dijkstra = new DijkstraAdjacencyVector(adjacencyVector);
        var result = dijkstra.execute(0, 4);
        var a = result;
    }

    public static void main(String[] args) {
        var app = new App();
        app.dijkstra();
    }
}
