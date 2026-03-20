package ru.otus.danilchenko.algorithms.lesson16;

import ru.otus.danilchenko.algorithms.lesson14.performance.adjacency_vector.AdjacencyVector;
import ru.otus.danilchenko.algorithms.lesson16.sceleton.IKrascal;
import ru.otus.danilchenko.algorithms.lesson16.sceleton.KrascalAdjacencyVector;

public class App {
    void krascal(){
        AdjacencyVector adjacencyVector = new AdjacencyVector(7, 4);
        adjacencyVector.set(0, 0, 1 ,2);
        adjacencyVector.set(0, 1, 2 ,2);
        adjacencyVector.set(0, 2, 4 ,1);
        adjacencyVector.set(0, 3, 6 ,3);

        adjacencyVector.set(1, 0, 2, 3);
        adjacencyVector.set(1, 1, 3, 3);

        adjacencyVector.set(2, 0, 0, 2);
        adjacencyVector.set(2, 1, 1, 3);

        adjacencyVector.set(3, 0, 1, 3);
        adjacencyVector.set(3, 1, 4, 2);

        adjacencyVector.set(4, 0, 3, 2);
        adjacencyVector.set(4, 1, 5, 4);

        adjacencyVector.set(5, 0,4,4);
        adjacencyVector.set(5, 1, 6, 2);

        IKrascal krascal = new KrascalAdjacencyVector(adjacencyVector);
        var result  = krascal.execute();
        var a = result;
    }

    public static void main(String[] args) {
        App app = new App();
        app.krascal();
    }
}
