package ru.otus.danilchenko.algorithms.lesson6.report;

public class SortingReportData {
    private final int arraySize;
    private final int numberOfComparisons;
    private final int numberOfExchanges;

    public SortingReportData(int arraySize, int numberOfComparisons, int numberOfExchanges) {
        this.arraySize = arraySize;
        this.numberOfComparisons = numberOfComparisons;
        this.numberOfExchanges = numberOfExchanges;
    }

    public int getArraySize() {
        return arraySize;
    }

    public int getNumberOfComparisons() {
        return numberOfComparisons;
    }

    public int getNumberOfExchanges() {
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
