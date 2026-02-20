package ru.otus.danilchenko.algorithms.report;

public class TreeReportData {
    private final int arrSize;
    private final long insertExecutionTime;
    private final long removeExecutionTime;
    private final long searchExecutionTime;

    public TreeReportData(int arrSize, long insertExecutionTime, long removeExecutionTime, long searchExecutionTime) {
        this.arrSize = arrSize;
        this.insertExecutionTime = insertExecutionTime;
        this.removeExecutionTime = removeExecutionTime;
        this.searchExecutionTime = searchExecutionTime;
    }

    public long getInsertExecutionTime() {
        return insertExecutionTime;
    }

    public long getRemoveExecutionTime() {
        return removeExecutionTime;
    }

    public long getSearchExecutionTime() {
        return searchExecutionTime;
    }

    public int getArrSize() {
        return arrSize;
    }

    @Override
    public String toString() {
        return "TreeReportData{" +
                "arrSize=" + arrSize +
                ", insertExecutionTime=" + insertExecutionTime +
                ", removeExecutionTime=" + removeExecutionTime +
                ", searchExecutionTime=" + searchExecutionTime +
                '}';
    }
}
