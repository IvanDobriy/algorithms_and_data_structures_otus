package ru.otus.danilchenko.algorithms.lesson16.sceleton;

import ru.otus.danilchenko.algorithms.lesson14.performance.adjacency_vector.AdjacencyVector;
import ru.otus.danilchenko.algorithms.lesson14.performance.set.Edge;
import ru.otus.danilchenko.algorithms.lesson4.dynamic_arrays.IArray;
import ru.otus.danilchenko.algorithms.lesson4.dynamic_arrays.SingleArray;
import ru.otus.danilchenko.algorithms.sort.IComparator;

import java.util.Objects;

public class KrascalAdjacencyVector implements IKrascal<Integer> {
    private final AdjacencyVector adjacencyVector;
    private final IComparator<Edge<Integer>> comparator;
    private Integer weight;
    private IArray<Edge<Integer>> result;

    public KrascalAdjacencyVector(AdjacencyVector adjacencyVector) {
        Objects.requireNonNull(adjacencyVector);
        this.adjacencyVector = adjacencyVector;
        this.comparator = (Edge<Integer> e1, Edge<Integer> e2) -> {
            return e1.getWeight() - e2.getWeight();
        };
        this.weight = 0;
        this.result = null;
    }

    private IArray<Edge<Integer>> getSortedEdges(){
        IArray<Edge<Integer>> result = new SingleArray<>(0);
        boolean[] isHandled = new boolean[adjacencyVector.getVertexSize()];
        for(int i = 0; i < adjacencyVector.getVertexSize(); i++){
            if(isHandled[i]){
                continue;
            }
            for(int j = 0; j < adjacencyVector.getMaxAdjacencyDegree(); j++){
                if(isHandled[j]){
                    continue;
                }

            }
        }
    }

    @Override
    public IArray<Edge<Integer>> execute() {
        if(result != null){
            return result;
        }
        IArray<Edge<Integer>> sortedEdges =  getSortedEdges();

        return null;
    }
}
