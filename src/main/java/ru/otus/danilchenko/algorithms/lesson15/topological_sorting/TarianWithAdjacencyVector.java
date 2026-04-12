package ru.otus.danilchenko.algorithms.lesson15.topological_sorting;

import ru.otus.danilchenko.algorithms.lesson14.performance.adjacency_vector.AdjacencyVector;
import ru.otus.danilchenko.algorithms.lesson4.dynamic_arrays.IArray;
import ru.otus.danilchenko.algorithms.lesson4.dynamic_arrays.SingleArray;
import ru.otus.danilchenko.algorithms.lesson4.stack.ArrayStack;
import ru.otus.danilchenko.algorithms.lesson4.stack.IStack;

import java.util.Objects;

public class TarianWithAdjacencyVector implements ITarian<Integer> {
    private final AdjacencyVector adjacencyVector;

    private enum State {NONE, SEEN, USED}

    private final State[] states;
    private IArray<Integer> result;
    private final IStack<Integer> stack;


    public TarianWithAdjacencyVector(AdjacencyVector adjacencyVector) {
        Objects.requireNonNull(adjacencyVector);
        this.adjacencyVector = adjacencyVector;
        this.states = new State[adjacencyVector.getVertexSize()];
        result = null;
        stack = new ArrayStack<>(new SingleArray<>(0));
        for (int i = 0; i < this.states.length; i++) {
            this.states[i] = State.NONE;
        }
    }

    private boolean dfs(int vertex) {
        states[vertex] = State.SEEN;
        Integer av;
        for (int i = 0; i < adjacencyVector.getMaxAdjacencyDegree(); i++) {
            av = adjacencyVector.get(vertex, i);
            if (av < 0) {
                continue;
            }
            if (states[av] != State.NONE) {
                continue;
            }
            if (!dfs(av)) {
                return false;
            }
        }
        states[vertex] = State.USED;
        stack.push(vertex);
        return true;
    }

    @Override
    public IArray<Integer> execute() {
        if (result != null) {
            return result;
        }
        for (int i = 0; i < adjacencyVector.getVertexSize(); i++) {
            if (states[i] != State.NONE) {
                continue;
            }
            if (!dfs(i)) {
                return result;
            }
        }
        result = new SingleArray<>(stack.size());
        for (int i = 0; i < result.size(); i++) {
            result.set(stack.pop(), i);
        }
        return result;
    }
}
