package ru.otus.danilchenko.algorithms.lesson16.sceleton;

public class UnionFind {
    private int[] parent;

    public UnionFind(int size) {
        parent = new int[size];
        for (int i = 0; i < size; i++) {
            parent[i] = i;
        }
    }

    public int find(int v) {
        if (v >= parent.length) {
            throw new IllegalArgumentException("v >= parent.length");
        }
        int parentV = parent[v];
        if (parentV == v) {
            return v;
        }
        parentV = find(parentV);
        parent[v] = parentV;
        return parentV;
    }

    public void union(int v1, int v2) {
        int parentV1 = find(v1);
        int parentV2 = find(v2);
        parent[parentV1] = parentV2;
    }
}