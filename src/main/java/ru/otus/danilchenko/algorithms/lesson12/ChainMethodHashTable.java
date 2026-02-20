package ru.otus.danilchenko.algorithms.lesson12;

import ru.otus.danilchenko.algorithms.lesson4.dynamic_arrays.IArray;
import ru.otus.danilchenko.algorithms.lesson4.dynamic_arrays.MatrixArray;
import ru.otus.danilchenko.algorithms.lesson4.dynamic_arrays.SingleArray;

import java.util.Objects;

public class ChainMethodHashTable<K, V> implements IHashTable<K, V> {
    private class Entry {
        K key;
        V value;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }


    private IHasher<K> hasher;
    IArray<IArray<Entry>> content;

    private int size;
    private int factor;

    public ChainMethodHashTable(IHasher<K> hasher, int size) {
        Objects.requireNonNull(hasher);
        this.hasher = hasher;
        this.content = new MatrixArray<>(size);
        this.size = 0;
        this.factor = 0;
    }

    private void rehache(){
        final int alfa = size /factor;
        IArray<IArray<Entry>> oldContent = content;
        if(alfa > 2){
            content = new MatrixArray<>(size *2);
            size = 0;
            factor = 0;
            IArray<Entry> list;
            Entry entry;
            for(int i = 0; i < oldContent.size(); i++){
                list = oldContent.get(i);
                if(list != null){
                    for(int j = 0; j < list.size(); j++){
                        entry = list.get(j);
                        insert(entry.key, entry.value);
                    }
                }
            }
        }
    }

    @Override
    public void insert(K key, V value) {
        final int position = getPosition(key);
        IArray<Entry> list = content.get(position);
        if (list == null) {
            list = new SingleArray<>(0);
            factor++;
        }
        list.add(new Entry(key, value), list.size());
        content.set(list, position);
        size++;
        rehache();
    }

    private int getPosition(K key) {
        final long hash = hasher.execute(key);
        return (int) (hash % content.size());
    }

    @Override
    public V find(K key) {
        final int position = getPosition(key);
        final var list = content.get(position);
        Entry entry;
        for (int i = 0; i < list.size(); i++) {
            entry = list.get(i);
            if (entry.key.equals(key)) {
                return entry.value;
            }
        }
        return null;
    }

    @Override
    public void remove(K key) {
        final int position = getPosition(key);
        final var list = content.get(position);
        if (list == null) {
            return;
        }
        if (list.size() == 0) {
            return;
        }
        Entry entry;
        for (int i = 0; i < list.size(); i++) {
            entry = list.get(i);
            if (entry != null && entry.key.equals(key)) {
                list.remove(i);
                if (list.size() == 0) {
                    factor--;
                }
                size--;
                break;
            }
        }
    }

    @Override
    public int size() {
        return size;
    }
}
