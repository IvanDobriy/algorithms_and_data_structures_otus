package ru.otus.danilchenko.algorithms.lesson15;

import ru.otus.danilchenko.algorithms.lesson14.performance.adjacency_vector.AdjacencyVector;
import ru.otus.danilchenko.algorithms.lesson15.topological_sorting.DMPWithAdjacencyVector;

public class App {
    public static void main(String[] args){
        final var av = new AdjacencyVector(6, 2);
        av.set(0, 0, 1);
        av.set(0, 1, 3);
        av.set(1, 0, 5);
        av.set(2, 0, 1);
        av.set(3, 0, 5);
        av.set(5, 0, 4);
        final var dmp = new DMPWithAdjacencyVector(av);
        var result = dmp.execute();
        var a = result;
    }
}
