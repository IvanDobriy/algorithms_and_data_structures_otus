package ru.otus.danilchenko.algorithms.lesson12;

import org.apache.poi.sl.draw.geom.GuideIf;

import java.util.Objects;

public class OpenAddressHashTable<K, V> implements IHashTable<K, V> {


    private final IHasher<K> hasher;

    public OpenAddressHashTable(IHasher<K> hasher, int size) {
        Objects.requireNonNull(hasher);
        this.hasher = hasher;

    }

    @Override
    public void insert(K key, V value) {

    }

    @Override
    public V find(K key) {
        return null;
    }

    @Override
    public void remove(K key) {

    }

    @Override
    public int size() {
        return 0;
    }
}
