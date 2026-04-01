package ru.otus.danilchenko.algorithms.lesson18;

import ru.otus.danilchenko.algorithms.lesson18.search.SearchFullScan;

public class App {
    private final String text;

    App() {
        text = "hello world";
    }

    private int searchFullScan(String pattern) {
        final SearchFullScan sfs = new SearchFullScan(pattern);
        return sfs.search(text);
    }

    public static void main(String[] args) {
        App app = new App();
        int pos = app.searchFullScan("world");
        int a = pos;
    }
}
