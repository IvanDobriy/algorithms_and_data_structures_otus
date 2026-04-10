package ru.otus.danilchenko.algorithms.lesson19.search;

import ru.otus.danilchenko.algorithms.collections.ITree;
import ru.otus.danilchenko.algorithms.lesson10.BinarySearchTree;
import ru.otus.danilchenko.algorithms.lesson12.Crc16;
import ru.otus.danilchenko.algorithms.lesson12.IHashTable;
import ru.otus.danilchenko.algorithms.lesson12.IHasher;
import ru.otus.danilchenko.algorithms.lesson12.OpenAddressHashTable;
import ru.otus.danilchenko.algorithms.lesson4.dynamic_arrays.IArray;
import ru.otus.danilchenko.algorithms.lesson4.dynamic_arrays.SingleArray;
import ru.otus.danilchenko.algorithms.sort.IComparator;

import java.util.Objects;

public class AutoSearch {
    private final IArray<Character> alphabet;
    private final IHashTable<Character, Integer> alphaPos;
    private final IArray<Integer> delta;
    private final Integer patternLength;

    private final IComparator<Character> comparator = (Character e1, Character e2) -> {
        return e1 - e2;
    };

    public AutoSearch(String pattern) {
        Objects.requireNonNull(pattern);
        patternLength = pattern.length();
        alphabet = createAlphabet(pattern);
        alphaPos = createAlphaPos(alphabet);

        IArray<Character> ptrn = new SingleArray<>(patternLength);
        for (int i = 0; i < patternLength; i++) {
            ptrn.set(pattern.charAt(i), i);
        }

        delta = createDelta(ptrn, alphabet);
    }

    public class CharHasher implements IHasher<Character> {
        private final Crc16 crc;

        public CharHasher(Crc16 crc) {
            Objects.requireNonNull(crc);
            this.crc = crc;
        }

        @Override
        public long execute(Character key) {
            byte[] rawData = String.valueOf(key).getBytes();
            return crc.calculate(rawData);
        }
    }


    private IHashTable<Character, Integer> createAlphaPos(IArray<Character> alphabet) {
        IHashTable<Character, Integer> result = new OpenAddressHashTable<>(new CharHasher(new Crc16(0xffff, 0xa001)), alphabet.size(), 1);
        for (int i = 0; i < alphabet.size(); i++) {
            result.insert(alphabet.get(i), i);
        }
        return result;
    }

    private IArray<Character> createAlphabet(String pattern) {
        final ITree<Character> result = new BinarySearchTree<>(comparator);
        Character character;
        for (int i = 0; i < pattern.length(); i++) {
            character = pattern.charAt(i);
            if (!result.search(character)) {
                result.insert(character);
            }
        }
        return result.toArray();
    }

    private int calcIndex(int row, int col) {
        return row * alphabet.size() + col;
    }

    private IArray<Character> getRange(IArray<Character> line, int from, int to) {
        IArray<Character> result = new SingleArray<>(to - from);
        int pos = 0;
        for (int i = from; i < to; i++) {
            result.set(line.get(i), pos++);
        }
        return result;
    }

    private IArray<Character> getPrefix(IArray<Character> line, int size) {
        return getRange(line, 0, size);
    }

    private IArray<Character> getSuffix(IArray<Character> line, int size) {
        return getRange(line, line.size() - size, line.size());
    }

    private boolean compare(IArray<Character> e1, IArray<Character> e2) {
        if (e1.size() != e2.size()) {
            return false;
        }
        for (int i = 0; i < e1.size(); i++) {
            if (comparator.compare(e1.get(i), e2.get(i)) != 0) {
                return false;
            }
        }
        return true;
    }

    private IArray<Integer> createDelta(IArray<Character> pattern, IArray<Character> alphabet) {
        IArray<Integer> result = new SingleArray<>((patternLength + 1) * alphabet.size());
        for (int i = 0; i < patternLength + 1; i++) {
            for (int j = 0; j < alphabet.size(); j++) {
                Character c = alphabet.get(j);
                int k = i + 1;
                if (k > patternLength) {
                    k--;
                }
                IArray<Character> line = getPrefix(pattern, i);
                line.add(c, line.size());
                IArray<Character> prefix = getPrefix(pattern, k);
                IArray<Character> suffix = getSuffix(line, k);
                while (((k > 0) && !compare(prefix, suffix))) {
                    k--;
                }
                int index = calcIndex(i, alphaPos.find(c));
                result.set(k, index);
            }
        }
        return result;
    }

    public int search(String text, int pos) {
        int q = 0;
        for (int i = pos; i < text.length(); i++) {
            var el = alphaPos.find(text.charAt(i));
            if (el == null) {
                q = 0;
            } else {
                q = delta.get(calcIndex(q, el));
            }
            if (q == patternLength) {
                return i - patternLength + 1;
            }
        }
        return -1;
    }
}
