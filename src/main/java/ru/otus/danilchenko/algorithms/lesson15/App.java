package ru.otus.danilchenko.algorithms.lesson15;

import ru.otus.danilchenko.algorithms.lesson14.performance.adjacency_vector.AdjacencyVector;
import ru.otus.danilchenko.algorithms.lesson15.search.DFSAdjacencyVector;
import ru.otus.danilchenko.algorithms.lesson15.topological_sorting.DMPWithAdjacencyVector;
import ru.otus.danilchenko.algorithms.lesson15.topological_sorting.TarianWithAdjacencyVector;

public class App {
    private final AdjacencyVector av;
    App(){
        av = new AdjacencyVector(6, 2);
        av.set(0, 0, 1);
        av.set(0, 1, 3);
        av.set(1, 0, 5);
        av.set(2, 0, 1);
        av.set(3, 0, 5);
        av.set(5, 0, 4);
    }


    private void dmp() {
        final var dmp = new DMPWithAdjacencyVector(av);
        var result = dmp.execute();
        var a = result;
    }
    private void dfc() {
       final var dfc = new DFSAdjacencyVector(av);
       final var result = dfc.execute(0, 4);
       var a = result;
    }

    private void tarian() {
        final var tarian = new TarianWithAdjacencyVector(av);
        final var result = tarian.execute();
        var a = result;
    }


    public static void main(String[] args) {
        final App app = new App();
        app.tarian();
    }
}
