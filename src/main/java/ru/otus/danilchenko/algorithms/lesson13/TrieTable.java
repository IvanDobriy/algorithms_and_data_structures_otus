package ru.otus.danilchenko.algorithms.lesson13;

import ru.otus.danilchenko.algorithms.collections.ITree;
import ru.otus.danilchenko.algorithms.lesson11.Treap;
import ru.otus.danilchenko.algorithms.lesson11.TreapTool;
import ru.otus.danilchenko.algorithms.lesson12.IHashTable;

public class TrieTable<K, V> implements IHashTable<String, V> {

    class Node {
        ITree<Node> tree;
        Character key;
        V value;

        private int compare(Node el1, Node el2) {
            return el1.key - el2.key;
        }

        Node() {
            this.key = null;
            value = null;
            tree = new Treap<>(new TreapTool<>(this::compare));
        }

        Node(char key) {
            super();
            this.key = key;
        }

        Node insert(int index, String word, V value) {
            Node node = new Node();
            node.key = word.charAt(index);
            Node sr = tree.searchWithValue(node);
            if(sr == null){
                tree.insert(node);
            }else {
                node = sr;
            }
            if (index == word.length() - 1) {
                node.value = value;
            }
            return node;
        }

        Node getNext(int index, String word) {
            char el = word.charAt(index);
            return tree.searchWithValue(new Node(el));
        }
    }

    private Node root;
    private int size;

    public TrieTable() {
        size = 0;
    }

    @Override
    public void insert(String key, V value) {
        if (root == null) {
            root = new Node();
        }
        Node node = root;
        for (int i = 0; i < key.length(); i++) {
            node = node.insert(i, key, value);
        }
        size++;
    }

    @Override
    public V find(String key) {
        if (root == null) {
            return null;
        }
        Node node = root;
        for (int i = 0; i < key.length(); i++) {
            node = node.getNext(i, key);
            if (node == null) {
                return null;
            }
        }
        return node.value;
    }

    @Override
    public void remove(String key) {
        if (root == null) {
            return;
        }
        Node node = root;
        for (int i = 0; i < key.length(); i++) {
            node = node.getNext(i, key);
            if (node == null) {
                return;
            }
        }
        node.value = null;
        size--;
    }

    @Override
    public int size() {
        return size;
    }
}
