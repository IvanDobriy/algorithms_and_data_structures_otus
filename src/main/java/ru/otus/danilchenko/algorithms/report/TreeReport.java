package ru.otus.danilchenko.algorithms.report;

public class TreeReport implements AutoCloseable {
    private final int treeSize;
    private final long insertExecutionTime;
    private final long removeExecutionTime;
    private final long searchExecutionTime;
    private final int rootHeight;

    public TreeReport(int treeSize, long insertExecutionTime, long removeExecutionTime, long searchExecutionTime, int rootHeight) {
        this.treeSize = treeSize;
        this.insertExecutionTime = insertExecutionTime;
        this.removeExecutionTime = removeExecutionTime;
        this.searchExecutionTime = searchExecutionTime;
        this.rootHeight = rootHeight;
    }

    public int getTreeSize() {
        return treeSize;
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

    public int getRootHeight() {
        return rootHeight;
    }

    @Override
    public String toString() {
        return "TreeReport{" +
                "treeSize=" + treeSize +
                ", insertExecutionTime=" + insertExecutionTime +
                ", removeExecutionTime=" + removeExecutionTime +
                ", searchExecutionTime=" + searchExecutionTime +
                ", rootHeight=" + rootHeight +
                '}';
    }

    @Override
    public void close() throws Exception {

    }
}
