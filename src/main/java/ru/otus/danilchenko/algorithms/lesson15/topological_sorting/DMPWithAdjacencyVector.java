package ru.otus.danilchenko.algorithms.lesson15.topological_sorting;

import ru.otus.danilchenko.algorithms.lesson14.performance.adjacency_vector.AdjacencyVector;
import ru.otus.danilchenko.algorithms.lesson4.dynamic_arrays.IArray;
import ru.otus.danilchenko.algorithms.lesson4.dynamic_arrays.SingleArray;

import java.util.Objects;

public class DMPWithAdjacencyVector implements IDMP<Integer> {
    private final AdjacencyVector adjacencyVector;
    private final IArray<Integer> indegreeArray;
    private final boolean[] isVisited;


    public DMPWithAdjacencyVector(AdjacencyVector adjacencyVector) {
        Objects.requireNonNull(adjacencyVector);
        this.adjacencyVector = adjacencyVector;
        this.indegreeArray = new SingleArray<>(adjacencyVector.getVertexSize());
        this.isVisited = new boolean[adjacencyVector.getVertexSize()];
    }

    @Override
    public IArray<IArray<Integer>> execute() {
        final IArray<IArray<Integer>> result = new SingleArray<>(0);
        int sum = 0;
        Integer indegree;
        int vertex;
        for (int i = 0; i < adjacencyVector.getVertexSize(); i++) {
            for (int j = 0; j < adjacencyVector.getMaxAdjacencyDegree(); j++) {
                vertex = adjacencyVector.get(i, j);
                if (vertex < 0) {
                    continue;
                }
                sum++;
                indegree = indegreeArray.get(vertex);
                if (indegree == null) {
                    indegreeArray.set(1, vertex);
                    continue;
                }
                indegree++;
                indegreeArray.set(indegree, vertex);
            }
        }
        int layerIndex = 0;
        IArray<Integer> layer;
        do {
            layer = null;
            for (int i = 0; i < adjacencyVector.getVertexSize(); i++) {
                if (isVisited[i]) {
                    continue;
                }
                indegree = indegreeArray.get(i);
                if (indegree != null && indegree > 0) {
                    continue;
                }
                isVisited[i] = true;
                if(result.size() == layerIndex){
                    layer = new SingleArray<>(0);
                    result.add(layer, layerIndex);
                }else {
                    layer = result.get(layerIndex);
                }
                layer.add(i, layer.size());
            }
            if(layer == null){
                return null;
            }
            for(int i =0; i < layer.size();i++){
                var layerVertex = layer.get(i);
                for (int j = 0; j < adjacencyVector.getMaxAdjacencyDegree(); j++) {
                    vertex = adjacencyVector.get(layerVertex, j);
                    if (vertex < 0) {
                        continue;
                    }
                    indegree = indegreeArray.get(vertex);
                    if (indegree == null) {
                        continue;
                    }
                    if (indegree == 0) {
                        continue;
                    }
                    indegree--;
                    indegreeArray.set(indegree, vertex);
                    sum--;
                }
            }
            layerIndex++;
        } while (sum > 0);
        for(int i = 0; i < isVisited.length; i++){
            if(!isVisited[i]){
                if(result.size() == layerIndex){
                    layer = new SingleArray<>(0);
                    result.add(layer, layerIndex);
                }else {
                    layer = result.get(layerIndex);
                }
                layer.add(i, layer.size());
            }
        }
        return result;
    }
}
