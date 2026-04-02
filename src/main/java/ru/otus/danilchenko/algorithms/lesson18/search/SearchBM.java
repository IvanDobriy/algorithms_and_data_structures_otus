package ru.otus.danilchenko.algorithms.lesson18.search;

import java.util.Objects;

public class SearchBM implements ISearch {
    private String pattern;

    public SearchBM(String pattern){
        Objects.requireNonNull(pattern);
        this.pattern = pattern;
    }

    @Override
    public int search(String text) {

        return 0;
    }
}
