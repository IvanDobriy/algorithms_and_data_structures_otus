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

    public OpenAddressHashTable(IHasher<K> hasher, int size, int step) {
        Objects.requireNonNull(hasher);
        this.hasher = hasher;
        container = new MatrixArray<>(size);
        isDeleted = new VectorArray<>(size);
        this.step = step;
        probing = 0;
    }

    private int getHash(K key, int i) {
        return (int) (hasher.execute(key) + i * step) % container.size();
    }


    @Override
    public void insert(K key, V value) {
        for (int i = 0; i < container.size(); i++) {
            int hash = getHash(key, i);
            probing++;
            if (isDeleted.get(hash) || container.get(hash) == null) {
                container.set(new Entry(key, value), hash);
                isDeleted.set(false, i);
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
                container.set(null, i);
                isDeleted.set(true, i);
                break;
            }
        }
    }

    @Override
    public int size() {
        return 0;
    }
}
