package ru.otus.danilchenko.algorithms.lesson8;

import ru.otus.danilchenko.algorithms.metrics.IExchangeCounter;
import ru.otus.danilchenko.algorithms.sort.IComparator;
import ru.otus.danilchenko.algorithms.sort.ISort;

import java.util.Objects;

public class MergeSort<T> implements ISort<T> {
    private final IComparator<T> comparator;
    private final IExchangeCounter exchangeCounter;

    public MergeSort(IComparator<T> comparator, IExchangeCounter exchangeCounter) {
        Objects.requireNonNull(comparator);
        Objects.requireNonNull(exchangeCounter);
        this.comparator = comparator;
        this.exchangeCounter = exchangeCounter;
    }


    private void msort(T[] arr, int leftIndex, int rightIndex) {
        if (leftIndex >= rightIndex) {
            return;
        }
        int middleIndex = (leftIndex + rightIndex) / 2;
        msort(arr, leftIndex, middleIndex);
        msort(arr, middleIndex + 1, rightIndex);
        merge(arr, leftIndex, middleIndex, rightIndex);
    }

    private void merge(T[] arr, int leftIndex, int middleIndex, int rightIndex) {
        T[] tempArray = (T[]) new Object[rightIndex - leftIndex + 1];
        int tempArrayIndex = 0;
        int left = leftIndex;
        int right = middleIndex + 1;
        while (left <= middleIndex && right <= rightIndex) {
            exchangeCounter.count(1);
            if (comparator.compare(arr[left], arr[right]) > 0) {
                tempArray[tempArrayIndex++] = arr[right++];
            } else {
                tempArray[tempArrayIndex++] = arr[left++];
            }
        }
        while (left <= middleIndex) {
            exchangeCounter.count(1);
            tempArray[tempArrayIndex++] = arr[left++];
        }
        while (right <= rightIndex) {
            exchangeCounter.count(1);
            tempArray[tempArrayIndex++] = arr[right++];
        }
        for (int i = leftIndex; i <= rightIndex; i++) {
            exchangeCounter.count(1);
            arr[i] = tempArray[i - leftIndex];
        }
    }

    @Override
    public T[] sort(T[] arr) {
        Objects.requireNonNull(arr);
        msort(arr, 0, arr.length - 1);
        return arr;
    }
}
