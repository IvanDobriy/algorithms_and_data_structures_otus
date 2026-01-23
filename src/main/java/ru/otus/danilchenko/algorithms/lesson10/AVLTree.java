package ru.otus.danilchenko.algorithms.lesson10;

import ru.otus.danilchenko.algorithms.collections.ITree;
import ru.otus.danilchenko.algorithms.lesson4.dynamic_arrays.SingleArray;
import ru.otus.danilchenko.algorithms.metrics.IExchangeCounter;
import ru.otus.danilchenko.algorithms.sort.IComparator;

import java.util.Objects;

public class AVLTree<T> implements ITree<T> {
    private IComparator<T> insertComparator;
    private IExchangeCounter insertExchangeCounter;
    private IComparator<T> searchComparator;
    private IExchangeCounter searchExchangeCounter;


    private Node root;

    private class Node {
        T key;
        Node left;
        Node right;

        int height;


        Node(T key) {
            Objects.requireNonNull(key);
            this.key = key;
        }
    }

    public AVLTree(IComparator<T> insertComparator,
                   IExchangeCounter insertExchangeCounter,
                   IComparator<T> searchComparator,
                   IExchangeCounter searchExchangeCounter
    ) {
        Objects.requireNonNull(insertComparator);

        Objects.requireNonNull(insertExchangeCounter);
        Objects.requireNonNull(searchComparator);
        Objects.requireNonNull(searchExchangeCounter);

        this.root = null;
        this.insertComparator = insertComparator;
        this.insertExchangeCounter = insertExchangeCounter;

        this.searchComparator = searchComparator;
        this.searchExchangeCounter = searchExchangeCounter;
    }


    private void insert(T key, Node currentNode) {
        if (root == null) {
            insertExchangeCounter.count(1);
            root = new Node(key);
            return;
        }
        if (currentNode == null) {
            insertExchangeCounter.count(1);
            currentNode = root;
        }
        if (insertComparator.compare(key, currentNode.key) < 0) {
            insertExchangeCounter.count(1);
            if (currentNode.left == null) {
                currentNode.left = new Node(key);
                return;
            }
            insert(key, currentNode.left);
            return;
        }
        insertExchangeCounter.count(1);
        if (currentNode.right == null) {
            currentNode.right = new Node(key);
            return;
        }
        insert(key, currentNode.right);

    }

    private class SearchResult {
        Node parent;
        Node node;

        SearchResult(Node parent, Node node) {
            Objects.requireNonNull(node);
            this.parent = parent;
            this.node = node;
        }
    }


    private SearchResult search(T key, IComparator<T> comparator, IExchangeCounter exchangeCounter, Node currentNode, Node previousNode) {
        if (root == null) {
            return null;
        }
        if (currentNode == null) {
            exchangeCounter.count(1);
            currentNode = root;
        }
        if (comparator.compare(key, currentNode.key) == 0) {
            return new SearchResult(previousNode, currentNode);
        }
        if (comparator.compare(key, currentNode.key) < 0) {
            if (currentNode.left == null) {
                return null;
            }
            return search(key, comparator, exchangeCounter, currentNode.left, currentNode);
        }
        if (currentNode.right == null) {
            return null;
        }
        return search(key, comparator, exchangeCounter, currentNode.right, currentNode);
    }

    @Override
    public void insert(T value) {
        insert(value, null);
    }

    @Override
    public boolean search(T value) {
        return search(value, searchComparator, searchExchangeCounter, null, null) != null;
    }

    @Override
    public void remove(T value) {
        SearchResult result = search(value, searchComparator, searchExchangeCounter, null, null);
        if (result == null) {
            return;
        }
        if (result.node.right == null) {
            if (result.parent == null) {
                root = result.node.left;
                return;
            }
            if (result.parent.left == result.node) {
                result.parent.left = result.node.left;
                return;
            }
            result.parent.right = result.node.left;
            return;
        }
        var minParent = result.node.right;
        var minNode = result.node.left;
        if (minNode == null) {
            if (result.parent == null) {
                minParent.left = root.left;
                root = minParent;
                return;
            }
            if (result.parent.left == result.node) {
                minParent.left = result.node.left;
                result.parent.left = minParent;
                return;
            }
            minParent.left = result.node.left;
            result.parent.right = minParent;
            return;
        }
        while (minNode.left != null) {
            minParent = minNode;
            minNode = minParent.left;
        }
        if (minNode == null) {
            if (result.parent == null) {
                minNode.left = root.left;
                root = minNode;
                return;
            }
            if (result.parent.left == result.node) {
                result.parent.left = minNode;

            }

        }
    }

    @Override
    public SingleArray<T> toArray() {
        return null;
    }
}
