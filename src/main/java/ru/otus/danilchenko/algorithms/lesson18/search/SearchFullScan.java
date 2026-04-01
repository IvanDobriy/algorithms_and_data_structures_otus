package ru.otus.danilchenko.algorithms.lesson18.search;

import java.util.Objects;

public class SearchFullScan implements ISearch {
    private String pattern;

    public SearchFullScan(String pattern) {
        Objects.requireNonNull(pattern);
        this.pattern = pattern;
    }

    @Override
    public int search(String text) {
        int tab = 0;
        int pos = 0;
        while (tab <= text.length() - pattern.length()) {
            while ((pos < pattern.length()) && (pattern.charAt(pos) == text.charAt(pos + tab))) {
                pos++;
            }
            if (pos == pattern.length()) {
                return tab;
            }
            tab++;
        }
        return -1;
    }
}
