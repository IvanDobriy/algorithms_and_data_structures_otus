package ru.otus.danilchenko.algorithms.lesson4.queue;

import ru.otus.danilchenko.algorithms.lesson4.dynamic_arrays.IArray;

import java.util.Objects;

public class Queue<T> implements IQueue<T> {

    private final IArray<T> array;
    private int index;

    public Queue(IArray<T> array){
        Objects.requireNonNull(array);
        this.array = array;
        index = -1;
    }

    @Override
    public void enqueue(T el) {
        index += 1;
        if(array.size() <= index){
            array.add(el, index);
            return;
        }
        array.set(el, index);
    }

    @Override
    public T dequeue() {
        if(index < 0){
            return null;
        }
        index--;
        return array.remove(0);
    }

    @Override
    public int size() {
        return index + 1;
    }
}
