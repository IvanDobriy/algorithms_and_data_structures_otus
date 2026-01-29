package ru.otus.danilchenko.algorithms.report;

public class TreeReportData {
    private final long insertExecutionTime;
    private final long removeExecutionTime;
    private final long searchExecutionTime;

    public TreeReportData(long insertExecutionTime, long removeExecutionTime, long searchExecutionTime) {
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

    @Override
    public String toString() {
        return "TreeReportData{" +
                "insertExecutionTime=" + insertExecutionTime +
                ", removeExecutionTime=" + removeExecutionTime +
                ", searchExecutionTime=" + searchExecutionTime +
                '}';
    }
}
