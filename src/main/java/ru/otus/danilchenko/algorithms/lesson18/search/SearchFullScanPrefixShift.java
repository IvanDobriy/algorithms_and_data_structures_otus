package ru.otus.danilchenko.algorithms.lesson18.search;

import ru.otus.danilchenko.algorithms.collections.ITree;
import ru.otus.danilchenko.algorithms.lesson11.Treap;
import ru.otus.danilchenko.algorithms.lesson11.TreapTool;
import ru.otus.danilchenko.algorithms.sort.IComparator;

import java.util.Objects;

public class SearchFullScanPrefixShift implements ISearch {
    private class Node {
        char el;
        int shift;

        Node(char el, int shift) {
            this.el = el;
            this.shift = shift;
        }
    }

    private final String pattern;
    private final ITree<Node> tree;


    public SearchFullScanPrefixShift(String pattern) {
        Objects.requireNonNull(pattern);
        this.pattern = pattern;
        IComparator<Node> comparator = (Node el1, Node el2) -> {
            return el1.el - el2.el;
        };
        tree = new Treap<>(new TreapTool<>(comparator));
        for(int i = 0; i < pattern.length(); i++){
            tree.insert(new Node(pattern.charAt(i), 1));
        }
    }

    @Override
    public int search(String text) {
        Objects.requireNonNull(text);
        int pos = 0;
        int tab = 0;
        Node node;
        while (tab <= text.length() - pattern.length()) {
            pos = 0;
            while ((pos < pattern.length()) && (pattern.charAt(pos) == text.charAt(pos + tab))) {
                pos++;
            }
            if (pos == pattern.length()) {
                return tab;
            }
            node = tree.searchWithValue(new Node(text.charAt(tab + pattern.length() -1), 0));
            if (node != null) {
                tab += node.shift;
            } else {
                tab += pattern.length();
            }
        }
        return -1;
    }
}
