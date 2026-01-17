package ru.otus.danilchenko.algorithms.lesson10;

import ru.otus.danilchenko.algorithms.collections.ITree;
import ru.otus.danilchenko.algorithms.lesson4.dynamic_arrays.SingleArray;
import ru.otus.danilchenko.algorithms.metrics.IExchangeCounter;
import ru.otus.danilchenko.algorithms.sort.IComparator;

import java.util.Objects;

public class BinarySearchTree<T> implements ITree<T> {
    private IComparator<T> comparator;
    private IExchangeCounter exchangeCounter;

    private Node root;

    private class StackNode {
        public StackNode previous;
        public Node value;

        public StackNode(Node value, StackNode previous) {
            Objects.requireNonNull(value);
            this.value = value;
            this.previous = previous;
        }
    }

    private class Node {
        public T key;
        public Node left;
        public Node right;

        public Node(T key) {
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
        if (root == null) {
            root = new Node(value);
            return;
        }
        Node current = root;
        do {
            if (comparator.compare(current.key, value) < 0) {
                if (current.left == null) {
                    current.left = new Node(value);
                    return;
                }
                current = current.left;
                continue;
            }
            if (current.right == null) {
                current.right = new Node(value);
                return;
            }
            current = current.right;
        } while (true);
    }

    @Override
    public boolean search(T value) {
        if (root == null) {
            return false;
        }
        Node current = root;
        do {
            if (comparator.compare(value, current.key) == 0) {
                return true;
            }
            if (comparator.compare(value, current.key) < 0) {
                if (current.left == null) {
                    return false;
                }
                current = current.left;
                continue;
            }
            if (current.right == null) {
                return false;
            }
            current = current.right;
        } while (true);
    }

    @Override
    public void remove(T value) {
        if (root == null) {
            return;
        }

//        if (root.key == value) {
//
//        }
//
//        Node current = root;
//        Node previous = null;
//        do {
//            if (comparator.compare())
//        } while (true);
    }

    @Override
    public SingleArray<T> toArray() {
        final SingleArray<T> result = new SingleArray<>(0);
        if (root == null) {
            return result;
        }
        var stackNode = new StackNode(root, null);
        while (stackNode != null) {
            var current = stackNode.value;
            while (current.left != null) {
                current = current.left;
                stackNode = new StackNode(current, stackNode);
            }
            result.add(stackNode.value.key, result.size());
            if (current.right == null) {
                stackNode = stackNode.previous;
                continue;
            }
            stackNode = new StackNode(current.right, stackNode);
        }
        return result;
    }
}
