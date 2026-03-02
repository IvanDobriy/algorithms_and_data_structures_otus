package ru.otus.danilchenko.algorithms.lesson14;

import ru.otus.danilchenko.algorithms.lesson4.dynamic_arrays.SingleArray;

public class App {

    public static void main(String[] args) {
        final var vertexs = new SingleArray<Vertex<Integer>>(10);
        for(int i = 0; i < 7; i++) {
            vertexs.add(new Vertex<>(i, i + 1), vertexs.size());

        }
        int index = 0;
        final var edges = new SingleArray<Edge<Integer>>(10 * 9 /2);
        edges.add(new Edge<>(vertexs.get(0), vertexs.get(3)), index++);
        edges.add(new Edge<>(vertexs.get(0), vertexs.get(4)), index++);
        edges.add(new Edge<>(vertexs.get(1), vertexs.get(3)), index++);
        edges.add(new Edge<>(vertexs.get(1), vertexs.get(4)), index++);
        edges.add(new Edge<>(vertexs.get(1), vertexs.get(5)), index++);
        edges.add(new Edge<>(vertexs.get(2), vertexs.get(4)), index++);
        edges.add(new Edge<>(vertexs.get(2), vertexs.get(5)), index++);
        edges.add(new Edge<>(vertexs.get(2), vertexs.get(6)), index++);

        final var setPerformance = new SetPerformance<Integer>(vertexs, edges);
    }

}
