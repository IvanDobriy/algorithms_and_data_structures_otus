package ru.otus.danilchenko.algorithms.lesson10;

import ru.otus.danilchenko.algorithms.collections.ITree;
import ru.otus.danilchenko.algorithms.lesson4.dynamic_arrays.SingleArray;
import ru.otus.danilchenko.algorithms.metrics.IExchangeCounter;
import ru.otus.danilchenko.algorithms.sort.IComparator;

import java.util.Objects;

public class BinarySearchTree<T> implements ITree<T> {

    private IComparator<T> insertComparator;
    private IExchangeCounter insertExchangeCounter;

    private IComparator<T> searchComparator;
    private IExchangeCounter searchExchangeCounter;

    private IComparator<T> removeComparator;
    private IExchangeCounter removeExchangeCouter;


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
            IComparator<T> insertComparator,
            IExchangeCounter insertExchangeCounter,
            IComparator<T> removeComparator,
            IExchangeCounter removeExchangeCouter,
            IComparator<T> searchComparator,
            IExchangeCounter searchExchangeCounter
    ) {
        Objects.requireNonNull(insertComparator);
        Objects.requireNonNull(insertExchangeCounter);
        Objects.requireNonNull(removeComparator);
        Objects.requireNonNull(removeExchangeCouter);
        Objects.requireNonNull(searchComparator);
        Objects.requireNonNull(searchExchangeCounter);
        this.insertComparator = insertComparator;
        this.insertExchangeCounter = insertExchangeCounter;
        this.removeComparator = removeComparator;
        this.removeExchangeCouter = removeExchangeCouter;
        this.searchComparator = searchComparator;
        this.searchExchangeCounter = searchExchangeCounter;
        root = null;
    }

    @Override
    public void insert(T value) {
        Objects.requireNonNull(value);
        final var comparator = insertComparator;
        final var exchangeCounter = insertExchangeCounter;
        if (root == null) {
            root = new Node(value);
            return;
        }
        Node current = root;
        do {
            exchangeCounter.count(1);
            if (comparator.compare(value, current.key) < 0) {
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
        Objects.requireNonNull(value);
        final var comparator = searchComparator;
        final var exchangeCounter = searchExchangeCounter;
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
                exchangeCounter.count(1);
                current = current.left;
                continue;
            }
            if (current.right == null) {
                return false;
            }
            exchangeCounter.count(1);
            current = current.right;
        } while (true);
    }


    private Node findParent(T value) {
        Objects.requireNonNull(value);

        final var comparator = removeComparator;
        final var exchangeCounter = removeExchangeCouter;

        if (root == null) {
            return null;
        }
        Node current = root;
        Node previous = null;
        do {
            if (comparator.compare(value, current.key) == 0) {
                return previous;
            }
            if (comparator.compare(value, current.key) < 0) {
                if (current.left == null) {
                    return null;
                }
                exchangeCounter.count(2);
                previous = current;
                current = current.left;
                continue;
            }
            if (current.right == null) {
                return null;
            }
            exchangeCounter.count(2);
            previous = current;
            current = current.right;
        } while (true);
    }

    @Override
    public void remove(T value) {
        Objects.requireNonNull(value);

        final var comparator = removeComparator;
        final var exchangeCounter = removeExchangeCouter;

        if (root == null) {
            return;
        }

        Node parent = findParent(value);
        Node current = null;


        exchangeCounter.count(1);
        if (parent == null) {
            current = root;
        } else if (parent.left != null && comparator.compare(parent.left.key, value) == 0) {
            current = parent.left;
        } else if (parent.right != null && comparator.compare(parent.right.key, value) == 0) {
            current = parent.right;
        }

        if (current == null) {
            return;
        }
        if (current.right == null) {
            exchangeCounter.count(1);
            if (current == root) {
                root = current.left;
                return;
            }
            parent.left = current.left;
            return;
        }

        Node minParent = current.right;
        Node minNode = minParent.left;
        if (minNode == null) {
            exchangeCounter.count(1);
            parent.right = minParent;
            return;
        }
        while (minNode.left != null) {
            exchangeCounter.count(2);
            minParent = minNode;
            minNode = minNode.left;
        }


        exchangeCounter.count(2);
        if (comparator.compare(parent.left.key, value) == 0) {
            parent.left = minNode;
        } else {
            parent.right = minNode;
        }
        minParent.left = minNode.right;
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
            while (current.right == null) {
                stackNode = stackNode.previous;
                if(stackNode == null){
                    break;
                }
                result.add(stackNode.value.key, result.size());
            }
            if(stackNode == null){
                continue;
            }
            stackNode = new StackNode(current.right, stackNode.previous);
        }
        return result;
    }
}

