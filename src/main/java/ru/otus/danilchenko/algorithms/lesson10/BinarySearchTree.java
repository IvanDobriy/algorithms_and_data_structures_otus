package ru.otus.danilchenko.algorithms.lesson10;

import ru.otus.danilchenko.algorithms.collections.ITree;
import ru.otus.danilchenko.algorithms.metrics.IExchangeCounter;
import ru.otus.danilchenko.algorithms.sort.IComparator;

import java.util.Objects;

public class BinarySearchTree<T> implements ITree<T> {
    private IComparator<T> comparator;
    private IExchangeCounter exchangeCounter;

    private Node root;

    private class Node {
        public T key;
        public Node left;
        public Node right;
        public Node(T key){
            this.key = key;
        }
    }

    public BinarySearchTree(
            IComparator<T> comparator,
            IExchangeCounter exchangeCounter
    ) {
        Objects.requireNonNull(comparator);
        Objects.requireNonNull(exchangeCounter);
        this.comparator = comparator;
        this.exchangeCounter = exchangeCounter;
        root = null;
    }

    @Override
    public void insert(T value) {
        
    }

    @Override
    public boolean search(T value) {
        return false;
    }

    @Override
    public void remove(T value) {

    }

    @Override
    public T[] toArray() {
        return null;
    }
}
