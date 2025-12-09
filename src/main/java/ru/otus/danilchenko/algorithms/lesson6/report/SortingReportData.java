package ru.otus.danilchenko.algorithms.lesson6.report;

public class SortingReportData {
    private final int arraySize;
    private final long numberOfComparisons;
    private final long numberOfExchanges;

    public SortingReportData(int arraySize, long numberOfComparisons, long numberOfExchanges) {
        this.arraySize = arraySize;
        this.numberOfComparisons = numberOfComparisons;
        this.numberOfExchanges = numberOfExchanges;
    }

    public int getArraySize() {
        return arraySize;
    }

    public long getNumberOfComparisons() {
        return numberOfComparisons;
    }

    public long getNumberOfExchanges() {
        return numberOfExchanges;
    }


    @Override
    public String toString() {
        return "SortingReportData{" +
                "arraySize=" + arraySize +
                ", numberOfComparisons=" + numberOfComparisons +
                ", numberOfExchanges=" + numberOfExchanges +
                '}';
    }
}
