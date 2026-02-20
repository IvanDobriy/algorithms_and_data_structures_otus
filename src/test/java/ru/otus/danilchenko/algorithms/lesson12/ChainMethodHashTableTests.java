package ru.otus.danilchenko.algorithms.lesson12;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ChainMethodHashTableTests {

    private static class Entry<K, V> {
        K key;
        V value;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private IHashTable<String, String> hashTable;

    @BeforeEach
    void beforeEach() {
        final var stringHasher = new StringHasher(new Crc16(0xffff, 0xa001));
        hashTable = new ChainMethodHashTable<>(stringHasher, 1);
    }

    @ParameterizedTest
    @MethodSource("dataProvider")
    void positiveTest(List<Entry<String, String>> data) {
        for (final var el : data) {
            hashTable.insert(el.key, el.value);
        }
        Assertions.assertEquals(3, hashTable.size());
        for (final var el : data) {
            final var result = hashTable.find(el.key);
            Assertions.assertEquals(result, el.value);
        }
        for (final var el : data) {
            hashTable.remove(el.key);
        }
        Assertions.assertEquals(0, hashTable.size());
        for (final var el : data) {
            final var result = hashTable.find(el.key);
            Assertions.assertNull(result);
        }
    }

    static Stream<List<Entry<String, String>>> dataProvider() {
        return Stream.of(List.of(new Entry<>("1", "dog"), new Entry<>("2", "cat"), new Entry<>("3", "owl")));
    }
}
