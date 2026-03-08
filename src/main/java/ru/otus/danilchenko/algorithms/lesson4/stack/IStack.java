package ru.otus.danilchenko.algorithms.lesson4.stack;

public interface IStack<T> {
    void push(T el);
    T pop();
    int size();
}
