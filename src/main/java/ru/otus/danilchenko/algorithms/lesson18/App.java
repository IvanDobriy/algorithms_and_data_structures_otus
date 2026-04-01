package ru.otus.danilchenko.algorithms.lesson18;

import ru.otus.danilchenko.algorithms.lesson18.search.SearchFullScan;
import ru.otus.danilchenko.algorithms.lesson18.search.SearchFullScanPostfixShift;
import ru.otus.danilchenko.algorithms.lesson18.search.SearchFullScanPrefixShift;

public class App {
    private final String text;

    App() {
        text = "hello wworworld";
    }

    private int searchFullScan(String pattern) {
        final SearchFullScan sfs = new SearchFullScan(pattern);
        return sfs.search(text);
    }

    private int searchFullScanPrefix(String pattern){
        final SearchFullScanPrefixShift sfsps = new SearchFullScanPrefixShift(pattern);
        return sfsps.search(text);
    }

    private int searchFullScanPostfix(String pattern){
        final SearchFullScanPostfixShift search = new SearchFullScanPostfixShift(pattern);
        return search.search(text);
    }



    public static void main(String[] args) {
        App app = new App();
        int pos = app.searchFullScanPostfix("world");
        int a = pos;
    }
}
