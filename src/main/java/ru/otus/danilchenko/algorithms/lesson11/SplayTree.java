package ru.otus.danilchenko.algorithms.lesson11;

import ru.otus.danilchenko.algorithms.collections.ITree;
import ru.otus.danilchenko.algorithms.lesson4.dynamic_arrays.SingleArray;
import ru.otus.danilchenko.algorithms.sort.IComparator;

import java.util.Objects;

public class SplayTree<T> implements ITree<T> {
    private final IComparator<T> comparator;
    private Node root;


    public SplayTree(IComparator<T> comparator) {
        Objects.requireNonNull(comparator);
        this.comparator = comparator;
    }

    private class Node {
        private T key;
        private Node left;
        private Node right;

        public Node(T key, Node left, Node right) {
            Objects.requireNonNull(key);
            this.key = key;
            this.left = left;
            this.right = right;
        }

        public void insert(T value) {
            if (root == null) {
                root = new Node(value, null, null);
                return;
            }
            if (comparator.compare(value, key) > 0) {
                if (this.right != null) {
                    this.right.insert(value);
                }
                this.right = new Node(value, null, null);
                return;
            }
            if (this.left != null) {
                this.left.insert(value);
            }
            this.left = new Node(value, null, null);
        }

        public void remove(T value, Node parent) {
            if (parent == null) {
                return;
            }

            if (comparator.compare(value, key) == 0) {
                if (right == null) {
                    if (parent.left == this) {
                        parent.left = left;
                    } else {
                        parent.right = left;
                    }
                    return;
                }
                Node minParent = right;
                Node min = right.left;
                if (min.left == null) {
                    if (parent.left == this) {
                        parent.left = minParent;
                    } else {
                        parent.right = minParent;
                    }
                    minParent.left = left;
                    return;
                }
                while (min.left != null) {
                    minParent = min;
                    min = min.left;
                }
                if (parent.left == this) {
                    parent.left = min;
                } else {
                    parent.right = min;
                }
                min.left = left;
                minParent.left = min.right;
            }
        }
    }


    @Override
    public void insert(T value) {
        if (root == null) {
            root = new Node();
        }
    }

    @Override
    public boolean search(T value) {
        return false;
    }

    @Override
    public void remove(T value) {

    }

    @Override
    public SingleArray<T> toArray() {
        return null;
    }
}
