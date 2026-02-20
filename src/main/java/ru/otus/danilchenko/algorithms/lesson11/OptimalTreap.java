package ru.otus.danilchenko.algorithms.lesson11;

import ru.otus.danilchenko.algorithms.collections.ITree;
import ru.otus.danilchenko.algorithms.lesson4.dynamic_arrays.SingleArray;

import java.util.Objects;
import java.util.Random;

public class OptimalTreap<T> implements ITree<T> {
    private final Random random = new Random();
    private final TreapTool<T> tool;

    public OptimalTreap(TreapTool<T> tool) {
        Objects.requireNonNull(tool);
        this.tool = tool;
    }

    @Override
    public void insert(T value) {
        tool.insert(value, random.nextInt() % 100);
    }

    @Override
    public boolean search(T value) {
        return tool.optimalTreeSearch(value) != null;
    }

    @Override
    public void remove(T value) {
        tool.remove(value);
    }

    @Override
    public T searchWithValue(T key) {
        return tool.searchWithValue(key);
    }

    @Override
    public SingleArray<T> toArray() {
        return tool.toArray();
    }
}
