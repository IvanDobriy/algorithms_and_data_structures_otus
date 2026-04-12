package ru.otus.danilchenko.algorithms.lesson12;

import ru.otus.danilchenko.algorithms.lesson4.dynamic_arrays.IArray;
import ru.otus.danilchenko.algorithms.lesson4.dynamic_arrays.MatrixArray;
import ru.otus.danilchenko.algorithms.lesson4.dynamic_arrays.VectorArray;

import java.util.Objects;

public class OpenAddressHashTable<K, V> implements IHashTable<K, V> {
    private class Entry {
        K key;
        V value;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private final IHasher<K> hasher;
    private IArray<Entry> container;
    private IArray<Boolean> isDeleted;
    private int step;
    private int probing;
    private int size;
    private int counter;

    public OpenAddressHashTable(IHasher<K> hasher, int size, int step) {
        Objects.requireNonNull(hasher);
        this.hasher = hasher;
        container = new MatrixArray<>(size);
        isDeleted = new VectorArray<>(size);
        for (int i = 0; i < isDeleted.size(); i++) {
            isDeleted.set(false, i);
        }
        this.step = step;
        probing = 0;
        this.size = 0;
        this.counter = 0;
    }

    private int getHash(K key, int i) {
        return (int) (hasher.execute(key) + i * step) % container.size();
    }


    private void rehash() {
        final double factor = (double) counter / container.size();
        if (factor > 0.7) {
            size = 0;
            probing = 0;
            IArray<Entry> oldContainer = container;
            isDeleted = new VectorArray<>(isDeleted.size() * 2);
            for (int i = 0; i < isDeleted.size(); i++) {
                isDeleted.set(false, i);
            }
            container = new MatrixArray<>(oldContainer.size() * 2);
            Entry data;
            for (int i = 0; i < oldContainer.size(); i++) {
                data = oldContainer.get(i);
                if (data != null) {
                    insert(data.key, data.value);
                }
            }
        }
    }

    @Override
    public void insert(K key, V value) {
        for (int i = 0; i < container.size(); i++) {
            int hash = getHash(key, i);
            probing++;
            if (isDeleted.get(hash)) {
                continue;
            }
            if (container.get(hash) == null) {
                container.set(new Entry(key, value), hash);
                isDeleted.set(false, i);
                size++;
                counter++;
                rehash();
                return;
            }
        }
    }

    @Override
    public V find(K key) {
        Entry result;
        int index = -1;
        for (int i = 0; i < container.size(); i++) {
            int hash = getHash(key, i);
            result = container.get(hash);
            if (isDeleted.get(i)) {
                index = i;
            }
            if (result != null && result.key.equals(key)) {
                if (index >= 0 && index != i) {
                    container.set(result, index);
                    isDeleted.set(false, index);
                    container.set(null, i);
                    isDeleted.set(true, i);
                }
                return result.value;
            }
        }
        return null;
    }

    @Override
    public void remove(K key) {
        Entry result;
        for (int i = 0; i < container.size(); i++) {
            int hash = getHash(key, i);
            result = container.get(hash);
            if (result != null && result.key.equals(key)) {
                container.set(null, hash);
                isDeleted.set(true, hash);
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
