package ru.otus.danilchenko.algorithms.lesson14.performance.incidence;

import ru.otus.danilchenko.algorithms.lesson4.dynamic_arrays.IArray;
import ru.otus.danilchenko.algorithms.lesson4.dynamic_arrays.SingleArray;

public class IncidenceMatrix {
    private IArray<Integer> container;
    private int edgeSize;
    private int vertexSize;

    private int calcIndex(int vertex, int edge) {
        return vertex * edgeSize + edge;
    }

    public IncidenceMatrix( int vertexSize, int edgeSize) {
        if (edgeSize < 0) {
            throw new IllegalArgumentException("edgeSize < 0");
        }
        if (vertexSize < 0) {
            throw new IllegalArgumentException("vertexSize < 0");
        }
        this.edgeSize = edgeSize;
        this.vertexSize = vertexSize;
        container = new SingleArray<>(edgeSize * vertexSize);
    }

    private void check(int vertex, int edge){
        if(vertex < 0 || vertex > vertexSize){
            throw new IllegalArgumentException(String.format("illegal vertex value: %d", vertex));
        }
        if(edge < 0 || edge > edgeSize){
            throw new IllegalArgumentException(String.format("illegal edge value: %d", edge));
        }
    }

    public void set(int vertex, int edge, int value) {
        check(vertex, edge);
        container.set(value, calcIndex(vertex, edge));
    }

    public int get(int vertex, int edge){
        check(vertex, edge);
        final Integer result = container.get(calcIndex(vertex,edge));
        if(result == null){
            return 0;
        }
        return result;
    }



}

