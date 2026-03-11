package ru.otus.danilchenko.algorithms.lesson15.topological_sorting;

import ru.otus.danilchenko.algorithms.lesson14.performance.adjacency_vector.AdjacencyVector;
import ru.otus.danilchenko.algorithms.lesson4.dynamic_arrays.IArray;
import ru.otus.danilchenko.algorithms.lesson4.dynamic_arrays.SingleArray;
import ru.otus.danilchenko.algorithms.lesson4.stack.ArrayStack;
import ru.otus.danilchenko.algorithms.lesson4.stack.IStack;

import java.util.Objects;

public class DFCAdjacencyVector implements IDFS {

    private enum VertexState {
        NONE,
        SEEN,
        USED
    }

    private class Edge {
        int begin;
        int end;

        Edge(int begin, int end) {
            this.begin = begin;
            this.end = end;
        }
    }

    private final AdjacencyVector adjacencyVector;
    private final VertexState states[];
    private final IStack<Edge> edges;
    private IArray<Integer> result;

    public DFCAdjacencyVector(AdjacencyVector adjacencyVector) {
        Objects.requireNonNull(adjacencyVector);
        this.adjacencyVector = adjacencyVector;
        states = new VertexState[adjacencyVector.getVertexSize()];
        for (int i = 0; i < states.length; i++) {
            states[i] = VertexState.NONE;
        }
        edges = new ArrayStack<>(new SingleArray<>(0));
        result = null;
    }

    @Override
    public IArray<Integer> execute(int begin, int end) {
        if (result != null) {
            return result;
        }
        if (begin == end) {
            return result;
        }

        boolean found = false;
        IStack<Integer> stack = new ArrayStack<>(new SingleArray<>(0));
        stack.push(begin);

        Edge edge;
        int adjacencyVertex;
        while (stack.size() > 0) {
            int vertex = stack.pop();
            states[vertex] = VertexState.USED;
            for (int i = 0; i < adjacencyVector.getMaxAdjacencyDegree(); i++) {
                adjacencyVertex = adjacencyVector.get(vertex, i);
                if (adjacencyVertex < 0) {
                    continue;
                }
                if (states[adjacencyVertex] == VertexState.USED) {
                    continue;
                }
                edge = new Edge(vertex, adjacencyVertex);
                edges.push(edge);
                if (adjacencyVertex == end) {
                    found = true;
                }
                if (states[adjacencyVertex] == VertexState.SEEN) {
                    continue;
                }
                stack.push(adjacencyVertex);
                states[adjacencyVertex] = VertexState.SEEN;
            }

        }
        if (found) {
            result = new SingleArray<>(0);
            int goal = end;
            int size = edges.size();
            for (int i = 0; i < size; i++) {
                edge = edges.pop();
                if (edge.end != goal) {
                    continue;
                }
                result.add(goal, result.size());
                goal = edge.begin;
            }
            result.add(begin, result.size());
            for (int i = 0; i < result.size()/2; i++) {
                adjacencyVertex = result.get(i);
                result.set(result.get(result.size() - 1 - i), i);
                result.set(adjacencyVertex, result.size() - 1 - i);
            }
        }

        return result;
    }
}
