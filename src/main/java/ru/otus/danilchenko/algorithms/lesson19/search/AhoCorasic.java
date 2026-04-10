package ru.otus.danilchenko.algorithms.lesson19.search;

import ru.otus.danilchenko.algorithms.lesson12.IHashTable;

import java.util.Objects;

public class AhoCorasic {
    private class Node{
        String prefix;
        String isFinal;
        Node suffixLink;
        Node finalLink;
        IHashTable<Character, Node> child;

        Node(String prefix){
            Objects.requireNonNull(prefix);
            this.prefix = prefix;
        }
    }
}
