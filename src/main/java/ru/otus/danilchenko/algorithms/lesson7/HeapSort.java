package ru.otus.danilchenko.algorithms.lesson7;


import ru.otus.danilchenko.algorithms.sort.IComparator;
import ru.otus.danilchenko.algorithms.sort.ISort;
import ru.otus.danilchenko.algorithms.sort.ISwap;

import java.util.Objects;

public class HeapSort<T> implements ISort<T> {
    private final IComparator<T> comparator;
    private final ISwap<T> swapper;

    public HeapSort(IComparator<T> comparator, ISwap<T> swapper) {
        Objects.requireNonNull(comparator);
        Objects.requireNonNull(swapper);
        this.comparator = comparator;
        this.swapper = swapper;
    }

    private void heapify(T[] arr, int root, int size) {
        int parent = root;
        int left = 2 * parent + 1;
        int right = 2 * parent + 2;
        if (left < size && comparator.compare(arr[left], arr[parent]) > 0) {
            parent = left;
        }
        if (right < size && comparator.compare(arr[right], arr[parent]) > 0) {
            parent = right;
        }
        if (parent == root) {
            return;
        }
        swapper.swap(arr, root, parent);
        heapify(arr, parent, size);
    }

    private void prepareHeap(T[] arr) {
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            heapify(arr, i, arr.length);
        }
    }

    @Override
    public T[] sort(T[] arr) {
        Objects.requireNonNull(arr);
        prepareHeap(arr);
        for (int i = arr.length - 1; i >= 0; i--) {
            swapper.swap(arr, i, 0);
            heapify(arr, 0, i);
        }
        return arr;
    }
}
