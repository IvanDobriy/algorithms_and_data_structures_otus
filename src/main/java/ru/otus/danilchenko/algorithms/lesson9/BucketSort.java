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
        T max = null;
        for (int i = 0; i < arr.length; i++) {
            if (comparator.compare(max, arr[i]) < 0) {
                exchangeCounter.count(1);
                max = arr[i];
            }
        }
        return max;
    }

    private int countIndex(T value, int maxIndex, int arrSize) {
        long amount = amounter.getAmount(value);
        return (int) ((amount * arrSize) / (maxIndex + 1));
    }


    private class Node {
        private  T value;
        private  Node next;

        public Node(T value, Node next) {
            this.value = value;
            this.next = next;
        }
    }


    @Override
    public T[] sort(T[] arr) {
        Objects.requireNonNull(arr);
        T max = getMaxElement(arr);
        int maxIndex = countIndex(max, amounter.getAmount(max), arr.length);
        IArray<Node> bucket = new SingleArray<>(maxIndex + 1);
        for (T element : arr) {
            int index = countIndex(element, maxIndex, arr.length);
            Node node = bucket.get(index);
            if (node == null) {
                bucket.add(new Node(element, null), index);
                continue;
            }
            if (comparator.compare(element, node.value) <= 0) {
                bucket.add(new Node(element, node), index);
                continue;
            }
            while (node.next != null) {
                if (comparator.compare(element, node.value) <= 0) {
                    exchangeCounter.count(1);
                    node = node.next;
                    continue;
                }
                break;
            }
            exchangeCounter.count(1);
            if (node.next == null) {
                node.next = new Node(element, null);
                continue;
            }
            Node nextNode = node.next;
            node.next = new Node(element, nextNode);
        }
        Node el;
        int j = 0;
        for(int i = 0 ; i < bucket.size(); i++){
            el = bucket.get(i);
            while (el != null){
                arr[j++] = el.value;
                el = el.next;
            }
        }
        return arr;
    }
}
