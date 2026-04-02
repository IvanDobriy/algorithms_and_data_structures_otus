package ru.otus.danilchenko.algorithms.lesson18.search;

import ru.otus.danilchenko.algorithms.lesson4.dynamic_arrays.IArray;
import ru.otus.danilchenko.algorithms.lesson4.dynamic_arrays.SingleArray;

import java.util.Objects;

public class SearchBM implements ISearch {
    private final String pattern;
    private final IArray<Integer> shift;

    public SearchBM(String pattern) {
        Objects.requireNonNull(pattern);
        this.pattern = pattern;
        shift = new SingleArray<>(pattern.length());
        shift.set(1, 0);
        int cnt;
        for (int i = 0; i < pattern.length(); i++) { // совпавший суффикс
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
                    if (cnt < i) {
                        if ((k + cnt) == pattern.length()) {
                            shift.set(k, i);
                            break;
                        }
                        continue;
                    }
                    shift.set(k, i);
                    break;
                }
            }
        }
    }

    @Override
    public int search(String text) {
        int tab = 0;
        int pos = 0;
        while (tab <= text.length() - pattern.length()) {
            pos = pattern.length() - 1;
            while ((pos >= 0) && (text.charAt(tab + pos) == pattern.charAt(pos))) {
                pos--;
            }
            if (pos < 0) {
                return tab;
            }
            tab += shift.get(pattern.length() - 1 - pos);
        }
        return -1;
    }
}
