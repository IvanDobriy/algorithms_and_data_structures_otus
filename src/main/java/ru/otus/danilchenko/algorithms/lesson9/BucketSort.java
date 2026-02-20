package ru.otus.danilchenko.algorithms.lesson9;

import ru.otus.danilchenko.algorithms.lesson4.dynamic_arrays.IArray;
import ru.otus.danilchenko.algorithms.lesson4.dynamic_arrays.SingleArray;
import ru.otus.danilchenko.algorithms.metrics.IExchangeCounter;
import ru.otus.danilchenko.algorithms.sort.IComparator;
import ru.otus.danilchenko.algorithms.sort.ISort;

import java.util.Objects;

public class BucketSort<T> implements ISort<T> {

    private final IComparator<T> comparator;
    private final IExchangeCounter exchangeCounter;
    private final IAmounter<T> amounter;


    public BucketSort(IComparator<T> comparator, IExchangeCounter exchangeCounter, IAmounter<T> amounter) {
        Objects.requireNonNull(comparator);
        Objects.requireNonNull(exchangeCounter);
        Objects.requireNonNull(amounter);
        this.comparator = comparator;
        this.exchangeCounter = exchangeCounter;
        this.amounter = amounter;
    }

    private T getMaxElement(T[] arr) {
        T max = arr[0];
        T value;
        for (int i = 1; i < arr.length; i++) {
            value = arr[i];
            if (comparator.compare(max, value) < 0) {
                exchangeCounter.count(1);
                max = value;
            }
        }
        return max;
    }

    private int countIndex(T value, int maxAmount, int arrSize) {
        long amount = amounter.getAmount(value);
        return (int) ((amount * arrSize) / (maxAmount + 1));
    }


    private class Node {
        private T value;
        private Node next;

        public Node(T value, Node next) {
            this.value = value;
            this.next = next;
        }
    }


    @Override
    public T[] sort(T[] arr) {
        Objects.requireNonNull(arr);
        if(arr.length == 0){
            return arr;
        }
        T max = getMaxElement(arr);
        int maxAmount = amounter.getAmount(max);
        int bucketSize = countIndex(max, maxAmount, arr.length) + 1;
        IArray<Node> bucket = new SingleArray<>(bucketSize);
        for (T element : arr) {
            int index = countIndex(element, maxAmount, arr.length);
            Node node = bucket.get(index);
            if (node == null) {
                exchangeCounter.count(1);
                bucket.set(new Node(element, null), index);
                continue;
            }
            Node prev = node;
            while (node.next != null) {
                if (comparator.compare(element, node.value) <= 0) {
                    exchangeCounter.count(1);
                    break;
                }
                prev = node;
                node = node.next;
            }
            exchangeCounter.count(1);
            if (prev == node) {
                if(comparator.compare(element, node.value) <= 0){
                    bucket.set(new Node(element, node), index);
                    continue;
                }
                node.next = new Node(element,  node.next);
                continue;
            }
            if(node.next == null){
                if(comparator.compare(element, node.value) > 0){
                    node.next = new Node(element,  node.next);
                    continue;
                }
            }
            prev.next = new Node(element, node);
        }
        Node el;
        int j = 0;
        for (int i = 0; i < bucket.size(); i++) {
            el = bucket.get(i);
            while (el != null) {
                exchangeCounter.count(2);
                arr[j++] = el.value;
                el = el.next;
            }
        }
        return arr;
    }
}
