package ru.otus.danilchenko.algorithms.lesson11;

import ru.otus.danilchenko.algorithms.collections.ITree;
import ru.otus.danilchenko.algorithms.lesson4.dynamic_arrays.SingleArray;
import ru.otus.danilchenko.algorithms.sort.IComparator;

import java.util.Objects;
import java.util.Random;

public class Treap<T> implements ITree<T> {
    private Random random = new Random();
    private IComparator<T> comparator;
    private Node internalTree;

    public Treap(IComparator<T> comparator) {
        Objects.requireNonNull(comparator);
        this.comparator = comparator;
    }


    private class SplitResult {
        Node leftPart;
        Node rightPart;
    }

    private class Node {
        private T key;
        private int power;
        private Node left;
        private Node right;

        public Node(T key, int power, Node left, Node right) {
            Objects.requireNonNull(key);
            this.key = key;
            this.power = power;
            this.left = left;
            this.right = right;
        }

        public T max() {
            var node = this;
            while (node.right != null) {
                node = node.right;
            }
            return node.key;
        }

        public void split(T key, SplitResult result, boolean eqToLeft) {
            boolean compResult;
            if (eqToLeft) {
                compResult = comparator.compare(key, this.key) >= 0;
            } else {
                compResult = comparator.compare(key, this.key) > 0;
            }

            if (compResult) {
                if (right != null) {
                    right.split(key, result, eqToLeft);
                }
                result.leftPart = new Node(this.key, this.power, left, result.leftPart);
                return;
            }

            if (left != null) {
                left.split(key, result, eqToLeft);
            }
            result.rightPart = new Node(this.key, this.power, result.rightPart, right);
        }

        public Node merge(Node rightPart) {
            if (rightPart == null) {
                return this;
            }
            if (power > rightPart.power) {
                if (right == null) {
                    return new Node(key, power, left, rightPart);
                }
                final var newRightPart = right.merge(rightPart);
                return new Node(key, power, left, newRightPart);
            }
            final var newLeftPart = this.merge(rightPart.left);
            return new Node(rightPart.key, rightPart.power, newLeftPart, rightPart.right);
        }

        public Node search(T value) {
            if (comparator.compare(value, this.key) == 0) {
                return this;
            }
            if (comparator.compare(value, this.key) > 0) {
                if (this.right == null) {
                    return null;
                }
                return this.right.search(value);
            }
            if (this.left == null) {
                return null;
            }
            return this.left.search(value);
        }

        public void toArray(SingleArray<T> result) {
            Objects.requireNonNull(result);
            if (left != null) {
                left.toArray(result);
            }
            result.add(key, result.size());
            if (right != null) {
                right.toArray(result);
            }
        }
    }

    @Override
    public void insert(T value) {
        Objects.requireNonNull(value);
        var node = new Node(value, random.nextInt() % 100, null, null);
        if (internalTree == null) {
            internalTree = node;
            return;
        }

        final SplitResult splitResult = new SplitResult();
        internalTree.split(value, splitResult, false);
        Node mergeResult = node;
        if (splitResult.leftPart != null) {
            mergeResult = splitResult.leftPart.merge(mergeResult);
        }
        internalTree = mergeResult.merge(splitResult.rightPart);
        ;
    }

    @Override
    public boolean search(T value) {
        if (internalTree == null) {
            return false;
        }
        return internalTree.search(value) != null;
    }

    @Override
    public void remove(T value) {
        Objects.requireNonNull(value);
        if (internalTree == null) {
            return;
        }
        final SplitResult splitResult1 = new SplitResult();
        internalTree.split(value, splitResult1, false);
        final SplitResult splitResult2  = new SplitResult();
        if(splitResult1.rightPart != null){
            splitResult1.rightPart.split(value, splitResult2, true);
        }
        if (splitResult1.leftPart != null) {
            internalTree = splitResult1.leftPart.merge(splitResult2.rightPart);
            return;
        }
        internalTree = splitResult2.rightPart;
    }

    @Override
    public SingleArray<T> toArray() {
        final SingleArray<T> result = new SingleArray<>(0);
        if (internalTree != null) {
            internalTree.toArray(result);
        }
        return result;
    }
}
