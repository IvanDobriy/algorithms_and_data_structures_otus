package ru.otus.danilchenko.algorithms.lesson16;

import ru.otus.danilchenko.algorithms.lesson14.performance.adjacency_vector.AdjacencyVector;
import ru.otus.danilchenko.algorithms.lesson16.sceleton.IKruscal;
import ru.otus.danilchenko.algorithms.lesson16.sceleton.IPrim;
import ru.otus.danilchenko.algorithms.lesson16.sceleton.KruscalAdjacencyVector;
import ru.otus.danilchenko.algorithms.lesson16.sceleton.PrimAdjacencyVector;

public class App {
    private final AdjacencyVector adjacencyVector;
    App(){
        adjacencyVector = new AdjacencyVector(7, 4);
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
    }

    void kruscal(){
        IKruscal kruscal = new KruscalAdjacencyVector(adjacencyVector);
        var result  = kruscal.execute();
        var a = result;
    }
    void prim() {
        IPrim prim = new PrimAdjacencyVector(adjacencyVector);
        var result =  prim.execute();
        var a = result;
    }

    public static void main(String[] args) {
        App app = new App();
        app.prim();
    }
}
