package ru.otus.danilchenko.algorithms.lesson13;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TrieTests {
    @Test
    void positiveTest(){
        final String data = "apple";
        final String data2 = "app";
        final Trie trie = new Trie();
        trie.insert(data);
        boolean sr = trie.search(data);
        Assertions.assertTrue(sr);
        sr = trie.search(data2);
        Assertions.assertFalse(sr);
        sr = trie.startsWith(data2);
        Assertions.assertTrue(sr);
    }
}
