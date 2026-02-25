package ru.otus.danilchenko.algorithms.lesson13;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import ru.otus.danilchenko.algorithms.lesson12.IHashTable;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TrieTableTests {
    @Test
    void positiveTest() {
        final String key = "1234";
        final String value = "Hello world";
        final IHashTable<String, String> container = new TrieTable<>();
        container.insert(key, value);
        String result = container.find(key);
        Assertions.assertEquals(result, value);
    }
}
