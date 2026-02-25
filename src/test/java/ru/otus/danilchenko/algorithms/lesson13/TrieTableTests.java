package ru.otus.danilchenko.algorithms.lesson13;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import ru.otus.danilchenko.algorithms.lesson12.IHashTable;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TrieTableTests {
    @Test
    void positiveTest() {
        final String key = "12";
        final String value = "Hello world";
        final IHashTable<String, String> container = new TrieTable<>();

//        String result = "";
        container.insert(key, value);
        String result = container.find(key);
        Assertions.assertEquals(value, result);

        Assertions.assertEquals(1, container.size());

        final String key2 = "1";
        final String value2 = "Hi";
        result = container.find(key2);
        Assertions.assertNull(result);

        container.insert(key2, value2);
        result = container.find(key2);
        Assertions.assertEquals(value2, result);
        Assertions.assertEquals(2, container.size());

        container.remove(key2);
        result = container.find(key2);
        Assertions.assertNull(result);
        result = container.find(key);
        Assertions.assertEquals(value, result);
    }
}
