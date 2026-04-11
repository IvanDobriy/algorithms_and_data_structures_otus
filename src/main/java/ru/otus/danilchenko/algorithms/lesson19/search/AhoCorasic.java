package ru.otus.danilchenko.algorithms.lesson19.search;

import ru.otus.danilchenko.algorithms.lesson12.Crc16;
import ru.otus.danilchenko.algorithms.lesson12.IHashTable;
import ru.otus.danilchenko.algorithms.lesson12.IHasher;
import ru.otus.danilchenko.algorithms.lesson12.OpenAddressHashTable;
import ru.otus.danilchenko.algorithms.lesson4.dynamic_arrays.IArray;
import ru.otus.danilchenko.algorithms.lesson4.dynamic_arrays.SingleArray;
import ru.otus.danilchenko.algorithms.lesson4.queue.ArrayQueue;
import ru.otus.danilchenko.algorithms.lesson4.queue.IQueue;

import java.util.Objects;

public class AhoCorasic {
    private class Node {
        String prefix;
        Boolean isFinal;
        Node suffixLink;
        Node finalLink;
        IHashTable<Character, Node> child;

        private class CharHasher implements IHasher<Character> {
            private final Crc16 crc16;

            CharHasher(Crc16 crc16) {
                Objects.requireNonNull(crc16);
                this.crc16 = crc16;
            }

            @Override
            public long execute(Character key) {
                byte[] data = String.valueOf(key).getBytes();
                return crc16.calculate(data);
            }
        }

        Node(String prefix) {
            Objects.requireNonNull(prefix);
            this.prefix = prefix;
            suffixLink = null;
            finalLink = null;
            isFinal = false;
            child = new OpenAddressHashTable<>(new CharHasher(new Crc16(0xffff, 0xa001)), prefix.length() + 1, 1);
        }
    }

    private final Node root;

    public AhoCorasic(IArray<String> patterns) {
        Objects.requireNonNull(patterns);
        root = new Node("");
        for (int i = 0; i < patterns.size(); i++) {
            addPattern(patterns.get(i));
        }
        buildLinks();
    }

    private void addPattern(String pattern) {
        Node node = root;
        Character c;
        for (int i = 0; i < pattern.length(); i++) {
            c = pattern.charAt(i);
            if (node.child.find(c) == null) {
                node.child.insert(c, new Node(node.prefix + c));
            }
            node = node.child.find(c);
        }
        node.isFinal = true;
    }

    private void buildLinks() {
        IQueue<Node> queue = new ArrayQueue<>(new SingleArray<>(0));
        Node node;
        for (int i = 0; i < root.prefix.length(); i++) {
            node = root.child.find(root.prefix.charAt(i));
            queue.enqueue(node);
            node.suffixLink = root;
        }
        Node child;
        Node suffix;
        Character c;
        while (queue.size() > 0) {
            node = queue.dequeue();
            for (int i = 0; i < node.prefix.length(); i++) {
                c = node.prefix.charAt(i);
                child = node.child.find(c);
                queue.enqueue(child);
                suffix = node.suffixLink;
                while (suffix != null && suffix.child.find(c) != null) {
                    suffix = suffix.suffixLink;
                }
                if (suffix == null) {
                    child.suffixLink = root;
                } else {
                    child.suffixLink = suffix.child.find(c);
                }
                if (child.suffixLink.isFinal) {
                    child.finalLink = child.suffixLink;
                } else {
                    child.finalLink = child.suffixLink.finalLink;
                }
            }
        }
    }

    public IArray<Integer> search(String text) {
        IArray<Integer> matches = new SingleArray<>(0);
        Node node = root;
        Character c;
        for (int i = 0; i < text.length(); i++) {
            c = text.charAt(i);
            while ((node != null) && (node.child.find(c) == null)) {
                node = node.suffixLink;
            }
            if (node == null) {
                node = root;
                continue;
            }
            node = node.child.find(c);
            if (node.isFinal) {
                matches.add(i - (node.prefix.length() - 1), matches.size());
            }
            Node finals = node.finalLink;
            while (finals != null) {
                matches.add(i - (finals.prefix.length() - 1), matches.size());
                finals = finals.finalLink;
            }
        }
        return matches;
    }
}
