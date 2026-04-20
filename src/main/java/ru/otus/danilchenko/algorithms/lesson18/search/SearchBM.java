package ru.otus.danilchenko.algorithms.lesson18.search;

import ru.otus.danilchenko.algorithms.collections.ITree;
import ru.otus.danilchenko.algorithms.lesson11.Treap;
import ru.otus.danilchenko.algorithms.lesson11.TreapTool;
import ru.otus.danilchenko.algorithms.lesson12.Crc16;
import ru.otus.danilchenko.algorithms.lesson12.IHashTable;
import ru.otus.danilchenko.algorithms.lesson12.OpenAddressHashTable;
import ru.otus.danilchenko.algorithms.lesson12.StringHasher;
import ru.otus.danilchenko.algorithms.lesson4.dynamic_arrays.IArray;
import ru.otus.danilchenko.algorithms.lesson4.dynamic_arrays.SingleArray;
import ru.otus.danilchenko.algorithms.sort.IComparator;

import java.util.Objects;

public class SearchBM implements ISearch {
    private class Node {
        char el;
        int shift;

        Node(char el, int shift) {
            this.el = el;
            this.shift = shift;
        }
    }

    private final String pattern;
    private final IArray<Integer> suffixShift;
    private final ITree<Node> badCharacterShift;

    private IArray<Integer> prepareSuffixShift() {
        IArray<Integer> result = new SingleArray<>(pattern.length());
        result.set(1, 0);
        int cnt;
        for (int i = 1; i < pattern.length(); i++) { // совпавший суффикс
            for (int k = 1; k <= pattern.length(); k++) { // поиск такого же суффикса
                cnt = 0;
                for (int j = 0; j < i; j++) {// количество совпавших символов
                    if ((j + k + 1) > pattern.length()) {// вышли за границы шаблона
                        break;
                    }
                    if (pattern.charAt(pattern.length() - 1 - j) != pattern.charAt(pattern.length() - 1 - j - k)) {
                        break;
                    }
                    cnt++;
                    if (cnt >= i) {
                        result.set(k, i);
                        break;
                    }
                    if ((k + cnt) == pattern.length()) {
                        result.set(k, i);
                        break;
                    }
                }
            }
        }
        return result;
    }

    private ITree<Node> prepareBadCharacterShift() {
        IComparator<Node> comparator = (Node el1, Node el2) -> {
            return el1.el - el2.el;
        };
        ITree<Node> result = new Treap<>(new TreapTool<>(comparator));
        Node node;
        for (int i = 0; i < pattern.length() - 1; i++) {
            node = result.searchWithValue(new Node(pattern.charAt(i), 1));
            if (node == null) {
                result.insert(new Node(pattern.charAt(i), pattern.length() - i - 1));
                continue;
            }
            node.shift = pattern.length() - i - 1;
        }
        return result;
    }

    public SearchBM(String pattern) {
        Objects.requireNonNull(pattern);
        this.pattern = pattern;
        suffixShift = prepareSuffixShift();
        badCharacterShift = prepareBadCharacterShift();
    }

    @Override
    public int search(String text) {
        int tab = 0;
        int pos = 0;
        int suffixShiftResult;
        int badCharacterShiftResult;
        Node badCharacterNode;
        while (tab <= text.length() - pattern.length()) {
            pos = pattern.length() - 1;
            while ((pos >= 0) && (text.charAt(tab + pos) == pattern.charAt(pos))) {
                pos--;
            }
            if (pos < 0) {
                return tab;
            }
            suffixShiftResult = suffixShift.get(pattern.length() - 1 - pos);
            badCharacterNode = badCharacterShift.searchWithValue(new Node(text.charAt(tab + pos), 0));
            if (badCharacterNode == null) {
                badCharacterShiftResult = pattern.length();
            } else {
                badCharacterShiftResult = badCharacterNode.shift;
            }
            tab += Math.max(suffixShiftResult, badCharacterShiftResult);
        }
        return -1;
    }
}
