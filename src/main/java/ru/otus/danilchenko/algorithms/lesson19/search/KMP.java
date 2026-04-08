package ru.otus.danilchenko.algorithms.lesson19.search;

import org.apache.commons.math3.optim.InitialGuess;
import ru.otus.danilchenko.algorithms.lesson4.dynamic_arrays.IArray;
import ru.otus.danilchenko.algorithms.lesson4.dynamic_arrays.SingleArray;

import java.util.Objects;

public class KMP {
    private final String pattern;

    private IArray<Integer> createPrefixIndex(String text) {
        String data = pattern + "#" + text;
        final IArray<Integer> prefixIndex = new SingleArray<>(data.length() + 1);
        for (int i = 0; i < prefixIndex.size(); i++) {
            prefixIndex.set(0, i);
        }
        try {
            for (int i = 1; i < data.length(); i++) {
                int len = prefixIndex.get(i);
                while ((len > 0) && (data.charAt(len) != data.charAt(i))) {
                    len = prefixIndex.get(len);
                }
                if (data.charAt(len) == data.charAt(i)) {
                    len++;
                }
                prefixIndex.set(len, i + 1);
            }
        } catch (Exception e) {
            var a = e;
        }
        return prefixIndex;
    }

    public KMP(String pattern) {
        Objects.requireNonNull(pattern);
        this.pattern = pattern;
    }

    public IArray<Integer> search(String text) {
        IArray<Integer> result = new SingleArray<>(0);
        IArray<Integer> prefixIndex = createPrefixIndex(text);
        for (int i = 0; i < prefixIndex.size(); i++) {
            if (prefixIndex.get(i) == pattern.length()) {
                result.add(i - pattern.length() * 2 -1, result.size());
            }
        }
        return result;
    }
}
