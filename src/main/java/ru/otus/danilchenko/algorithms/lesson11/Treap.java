package ru.otus.danilchenko.algorithms.lesson11;

import ru.otus.danilchenko.algorithms.collections.ITree;
import ru.otus.danilchenko.algorithms.lesson4.dynamic_arrays.SingleArray;

import java.util.Objects;
import java.util.Random;

public class Treap<T> implements ITree<T> {
    private final TreapTool<T> tool;
    private final Random random = new Random();

    public Treap(TreapTool<T> tool) {
        Objects.requireNonNull(tool);
        this.tool = tool;
    }

    @Override
    public void insert(T value) {
        tool.insert(value, random.nextInt() % 100);
    }

    @Override
    public boolean search(T value) {
        return tool.search(value);
    }

    @Override
    public void remove(T value) {
        tool.remove(value);
    }

    @Override
    public SingleArray<T> toArray() {
        return tool.toArray();
    }
}
