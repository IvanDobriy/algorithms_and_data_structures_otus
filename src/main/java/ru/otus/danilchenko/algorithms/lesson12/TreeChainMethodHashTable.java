package ru.otus.danilchenko.algorithms.lesson12;

import ru.otus.danilchenko.algorithms.collections.ITree;
import ru.otus.danilchenko.algorithms.lesson10.AVLTree;
import ru.otus.danilchenko.algorithms.lesson4.dynamic_arrays.IArray;
import ru.otus.danilchenko.algorithms.lesson4.dynamic_arrays.MatrixArray;
import ru.otus.danilchenko.algorithms.sort.IComparator;

import java.util.Objects;

public class TreeChainMethodHashTable<K, V> implements IHashTable<K, V> {
    private class Entry {
        K key;
        V value;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private IHasher<K> hasher;
    IArray<ITree<Entry>> content;

    private int size;
    private int factor;

    private final IComparator<Entry> comparator;
    private final IComparator<K> keyComparator;

    public TreeChainMethodHashTable(IHasher<K> hasher, IComparator<K> valueComparator, int size) {
        Objects.requireNonNull(hasher);
        Objects.requireNonNull(valueComparator);
        this.keyComparator = valueComparator;
        this.hasher = hasher;
        this.content = new MatrixArray<>(size);
        this.size = 0;
        this.factor = 0;
        comparator = (Entry el1, Entry el2) -> {
            return this.keyComparator.compare(el1.key, el2.key);
        };
    }

    private int getPosition(K key) {
        final long hash = hasher.execute(key);
        return (int) (hash % content.size());
    }


    private void rehache() {
        final int alfa = size / factor;
        IArray<ITree<Entry>> oldContent = content;
        if (alfa > 2) {
            content = new MatrixArray<>(size * 2);
            size = 0;
            factor = 0;
            ITree<Entry> tree;
            IArray<Entry> arr;
            Entry entry;
            for (int i = 0; i < oldContent.size(); i++) {
                tree = oldContent.get(i);
                if (tree != null) {
                    arr = tree.toArray();
                    for (int j = 0; j < arr.size(); j++) {
                        entry = arr.get(j);
                        insert(entry.key, entry.value);
                    }
                }
            }
        }
    }

    @Override
    public void insert(K key, V value) {
        Objects.requireNonNull(key);
        Objects.requireNonNull(value);
        final int position = getPosition(key);
        ITree<Entry> tree = content.get(position);
        if (tree == null) {
            tree = new AVLTree<>(comparator);
            factor++;
        }
        tree.insert(new Entry(key, value));
        content.set(tree, position);
        size++;
        rehache();
    }

    @Override
    public V find(K key) {
        Objects.requireNonNull(key);
        final int position = getPosition(key);
        final var list = content.get(position);
        Entry entry = new Entry(key, null);
        var result = list.searchWithValue(entry);
        if (result == null) {
            return null;
        }
        return result.value;
    }

    @Override
    public void remove(K key) {
        Objects.requireNonNull(key);
        final int position = getPosition(key);
        final var tree = content.get(position);
        if (tree == null) {
            return;
        }
        Entry entry = new Entry(key, null);
        tree.remove(entry);
        size--;
    }

    @Override
    public int size() {
        return size;
    }
}
