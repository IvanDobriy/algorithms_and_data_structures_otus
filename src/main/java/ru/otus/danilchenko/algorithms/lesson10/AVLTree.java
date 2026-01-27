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
    private IComparator<T> removeComparator;
    private IExchangeCounter removeExchangeCounter;


    private Node root;

    private class Node {
        T key;
        Node left;
        Node right;

        int height;


        Node(T key) {
            Objects.requireNonNull(key);
            this.key = key;
            this.height = 0;
        }
    }

    private void updateHeight(Node node) {
        if (node == null) {
            return;
        }
        int left = node.left == null ? -1 : node.left.height;
        int right = node.right == null ? -1 : node.right.height;
        node.height = 1 + Math.max(left, right);
    }

    private Node minLeftRotate(Node a) {
        final Node b = a.right;
        a.right = b.left;
        b.left = a;
        if (root == a) {
            root = b;
        }
        updateHeight(a);
        updateHeight(b);
        return b;
    }

    private Node minRightRotate(Node a) {
        final Node b = a.left;
        a.left = b.right;
        b.right = a;
        if (root == a) {
            root = b;
        }
        updateHeight(a);
        updateHeight(b);
        return b;
    }


    private Node maxLeftRotate(Node a) {
        a.right = minRightRotate(a.right);
        a = minLeftRotate(a);
        updateHeight(a);
        return a;
    }

    private Node maxRightRotate(Node a) {
        a.left = minLeftRotate(a.left);
        a = minRightRotate(a);
        updateHeight(a);
        return a;
    }

    private Node balanceTree(Node node) {
        if (node == null) {
            return null;
        }
        int left = node.left == null ? -1 : node.left.height;
        int right = node.right == null ? -1 : node.right.height;
        int balance = left - right;
        if (Math.abs(balance) < 2) {
            return node;
        }
        if (balance > 1) {
            Node b = node.left;
            left = b.left == null ? -1 : b.left.height;
            right = b.right == null ? -1 : b.right.height;
            balance = left - right;
            if (balance >= 0) {
                return minRightRotate(node);
            }
            return maxRightRotate(node);
        }
        Node b = node.right;
        left = b.left == null ? -1 : b.left.height;
        right = b.right == null ? -1 : b.right.height;
        balance = left - right;
        if (balance <= 0) {
            return minLeftRotate(node);
        }
        return maxLeftRotate(node);
    }

    private void rebalanceTree(Node node) {
        do {
            updateHeight(node);
            if (node == null) {
                break;
            }
            if (node.left != null) {
                node.left = balanceTree(node.left);
            }
            if (node.right != null) {
                node.right = balanceTree(node.right);
            }
            if (node == root) {
                root = balanceTree(root);
            }
            var result = search(node.key, removeComparator, removeExchangeCounter, null, null);
            if (result == null) {
                node = null;
            } else {
                node = result.parent;
            }
        } while (node != null);
    }

    public AVLTree(IComparator<T> insertComparator,
                   IExchangeCounter insertExchangeCounter,
                   IComparator<T> searchComparator,
                   IExchangeCounter searchExchangeCounter,
                   IComparator<T> removeComparator,
                   IExchangeCounter removeExchangeCounter
    ) {
        Objects.requireNonNull(insertComparator);
        Objects.requireNonNull(insertExchangeCounter);

        Objects.requireNonNull(searchComparator);
        Objects.requireNonNull(searchExchangeCounter);

        Objects.requireNonNull(removeComparator);
        Objects.requireNonNull(removeExchangeCounter);


        this.root = null;
        this.insertComparator = insertComparator;
        this.insertExchangeCounter = insertExchangeCounter;

        this.searchComparator = searchComparator;
        this.searchExchangeCounter = searchExchangeCounter;

        this.removeComparator = removeComparator;
        this.removeExchangeCounter = removeExchangeCounter;
    }

    private void rebalanceTreeAfterInsert(Node node) {
        updateHeight(node.left);
        updateHeight(node.right);
        node.left = balanceTree(node.left);
        node.right = balanceTree(node.right);
        updateHeight(node);
        if (node == root) {//todo как то сомнительно))
            root = balanceTree(root);
            updateHeight(root);
        }
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
            rebalanceTreeAfterInsert(currentNode);
            return;
        }
        insertExchangeCounter.count(1);
        if (currentNode.right == null) {
            currentNode.right = new Node(key);
            return;
        }
        insert(key, currentNode.right);
        rebalanceTreeAfterInsert(currentNode);
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
                rebalanceTree(root);
                return;
            }
            if (result.parent.left == result.node) {
                result.parent.left = result.node.left;
                rebalanceTree(result.parent);
                return;
            }
            result.parent.right = result.node.left;
            rebalanceTree(result.parent);
            return;
        }
        var minParent = result.node.right;
        var minNode = minParent.left;
        if (minNode == null) {
            if (result.parent == null) {
                minParent.left = root.left;
                root = minParent;
                rebalanceTree(minParent);
                return;
            }
            minParent.left = result.node.left;
            if (result.parent.left == result.node) {
                result.parent.left = minParent;
                rebalanceTree(minParent);
                return;
            }
            result.parent.right = minParent;
            rebalanceTree(minParent);
            return;
        }
        while (minNode.left != null) {
            minParent = minNode;
            minNode = minParent.left;
        }

        minParent.left = minNode.right;
        minNode.right = result.node.right;
        minNode.left = result.node.left;

        if (result.parent == null) {
            minNode.left = root.left;
            root = minNode;
            rebalanceTree(minParent);
            return;
        }

        if (result.parent.left == result.node) {
            result.parent.left = minNode;
            rebalanceTree(minParent);
            return;
        }
        result.parent.right = minNode;
        rebalanceTree(minParent);
    }

    private void prepareSorted(SingleArray<T> arr, Node node) {
        if (node == null) {
            return;
        }
        if (node.left != null) {
            prepareSorted(arr, node.left);
        }
        arr.add(node.key, arr.size());
        if (node.right != null) {
            prepareSorted(arr, node.right);
        }
    }

    @Override
    public SingleArray<T> toArray() {
        final SingleArray<T> result = new SingleArray<>(0);
        prepareSorted(result, root);
        return result;
    }
}
