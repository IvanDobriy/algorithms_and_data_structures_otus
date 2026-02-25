package ru.otus.danilchenko.algorithms.lesson13;

class Trie {
    private static char FIRST_CHAR = 'a';
    private static final int SIZE = 26;

    private class Node {
        Node[] chars;
        boolean isTerminal;

        Node() {
            chars = new Node[SIZE];
            isTerminal = false;
        }

        Node inset(int index, String word) {
            if (index > word.length()) {
                return null;
            }
            final char el = word.charAt(index);
            int position = el - FIRST_CHAR;
            if (chars[position] == null) {
                chars[position] = new Node();
            }
            if (index == word.length() - 1) {
                chars[position].isTerminal = true;
            }
            return chars[position];
        }

        Node getNext(int index, String word) {
            if (index > word.length()) {
                return null;
            }
            final char el = word.charAt(index);
            index = el - FIRST_CHAR;
            return chars[index];
        }
    }

    private Node root;

    public Trie() {

    }

    public void insert(String word) {
        if (root == null) {
            root = new Node();
        }
        Node node = root;
        for (int i = 0; i < word.length(); i++) {
            node = node.inset(i, word);
        }
    }

    public boolean search(String word) {
        if (root == null) {
            return false;
        }
        Node node = root;
        for (int i = 0; i < word.length(); i++) {
            node = node.getNext(i, word);
            if (node == null) {
                return false;
            }
        }
        return node.isTerminal;
    }

    public boolean startsWith(String prefix) {
        if (root == null) {
            return false;
        }
        Node node = root;
        for (int i = 0; i < prefix.length(); i++) {
            node = node.getNext(i, prefix);
            if (node == null) {
                return false;
            }
        }
        return true;
    }
}