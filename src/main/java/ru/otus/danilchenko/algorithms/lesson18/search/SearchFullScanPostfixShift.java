package ru.otus.danilchenko.algorithms.lesson18.search;

import ru.otus.danilchenko.algorithms.collections.ITree;
import ru.otus.danilchenko.algorithms.lesson11.Treap;
import ru.otus.danilchenko.algorithms.lesson11.TreapTool;
import ru.otus.danilchenko.algorithms.sort.IComparator;

import java.util.Objects;

public class SearchFullScanPostfixShift implements ISearch {
    private class Node {
        char el;
        int shift;

        Node(char el, int shift) {
            this.el = el;
            this.shift = shift;
        }
    }

    private final String pattern;
    private final ITree<Node> shift;

    public SearchFullScanPostfixShift(String pattern) {
        Objects.requireNonNull(pattern);
        this.pattern = pattern;
        IComparator<Node> comparator = (Node el1, Node el2) -> {
            return el1.el - el2.el;
        };
        shift = new Treap<>(new TreapTool<>(comparator));
        Node node;
        for (int i = 0; i < pattern.length(); i++) {
            node = shift.searchWithValue(new Node(pattern.charAt(i), 1));
            if (node == null) {
                shift.insert(new Node(pattern.charAt(i), pattern.length() - i - 1));
                continue;
            }
            node.shift = pattern.length() - i - 1;
        }
    }

    @Override
    public int search(String text) {
        Objects.requireNonNull(text);
        int tab = 0;
        int pos = 0;
        while (tab <= text.length() - pattern.length()) {
            pos = pattern.length() - 1;
            while ((pos >= 0) && (pattern.charAt(pos) == text.charAt(tab + pos))) {
                pos--;
            }
            if (pos < 0) {
                return tab;
            }
            Node node = shift.searchWithValue(new Node(text.charAt(tab + pattern.length() - 1), 0));
            if (node == null) {
                tab += pattern.length();
            } else {
                tab += node.shift;
            }
        }
        return -1;
    }
}
