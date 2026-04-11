package ru.otus.danilchenko.algorithms.lesson19;

import ru.otus.danilchenko.algorithms.lesson19.search.AhoCorasic;
import ru.otus.danilchenko.algorithms.lesson19.search.AutoSearch;
import ru.otus.danilchenko.algorithms.lesson19.search.KMP;
import ru.otus.danilchenko.algorithms.lesson4.dynamic_arrays.SingleArray;

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

    private void autosearch() {
        final var ats = new AutoSearch("world");
        final var result = ats.search(text, 0);
        final var a = result;
    }

    private void ahoCorasic() {
        final var patterns = new SingleArray<String>(0);
        patterns.add("world", patterns.size());
        final var ac = new AhoCorasic(patterns);
        final var result = ac.search(text);
        final var a = result;
    }

    public static void main(String[] args) {
        final App app = new App();
        app.ahoCorasic();
    }
}
