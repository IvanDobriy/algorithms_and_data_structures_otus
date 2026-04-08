package ru.otus.danilchenko.algorithms.lesson19;

import ru.otus.danilchenko.algorithms.lesson19.search.KMP;

public class App {
    private final String text;

    App() {
        text = "hello wworworld";
    }
    private void kmp() {
        final var kmp = new KMP("world");
        final var result = kmp.search(text);
        final var a = result;
    }

    public static void main(String[] args){
        final App app = new App();
        app.kmp();
    }
}
