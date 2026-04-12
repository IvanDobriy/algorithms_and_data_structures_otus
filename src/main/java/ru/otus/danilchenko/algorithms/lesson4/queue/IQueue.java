package ru.otus.danilchenko.algorithms.lesson4.queue;

public interface IQueue<T> {
    void enqueue(T el);
    T dequeue();
    int size();
}
