package ru.otus.danilchenko.algorithms.lesson4.dynamic_arrays;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.logging.Logger;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SingleArrayTests {
    private Logger logger = Logger.getLogger(this.getClass().getName());

    @Test
    void insertIntoEmptyArray() {
        IArray<Integer> array = new SingleArray<>(0);
        array.add(1, 0);
        Integer[] expected = new Integer[]{1};
        Assertions.assertEquals(expected.length, array.size());
        for (int i = 0; i < expected.length; i++) {
            Assertions.assertEquals(expected[i], array.get(i));
        }
    }

    @Test
    void insertIntoBeginOfArray() {
        FactorArray<Integer> array = new FactorArray<>(0);
        for (int i = 0; i < 10; i++) {
            array.add(i, 0);
        }
        Integer[] expected = new Integer[]{9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
        Assertions.assertEquals(expected.length, array.size());
        for (int i = 0; i < expected.length; i++) {
            Assertions.assertEquals(expected[i], array.get(i));
        }
    }

    @Test
    void insertIntoEndOfArray() {
        IArray<Integer> array = new SingleArray<>(0);
        for (int i = 0; i < 10; i++) {
            array.add(i, i);
        }
        Integer[] expected = new Integer[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        Assertions.assertEquals(expected.length, array.size());
        for (int i = 0; i < expected.length; i++) {
            Assertions.assertEquals(expected[i], array.get(i));
        }
    }

    @Test
    void insertIntoMiddleOfArray() {
        IArray<Integer> array = new SingleArray<>(0);
        for (int i = 0; i < 3; i++) {
            array.add(i, i);
        }
        Integer[] expected = new Integer[]{0, 0, 1, 2, 1, 2};
        for (int i = 0; i < 3; i++) {
            array.add(i, i + 1);
        }
        Assertions.assertEquals(expected.length, array.size());
        for (int i = 0; i < array.size(); i++) {
            Assertions.assertEquals(expected[i], array.get(i));
        }
    }

    @Test
    void insertIntoMiddleOfArraySameIndex() {
        IArray<Integer> array = new SingleArray<>(0);
        for (int i = 0; i < 3; i++) {
            array.add(i, i);
        }
        Integer[] expected = new Integer[]{0, 2, 1, 0, 1, 2};
        for (int i = 0; i < 3; i++) {
            array.add(i, 1);
        }
        Assertions.assertEquals(expected.length, array.size());
        for (int i = 0; i < array.size(); i++) {
            Assertions.assertEquals(expected[i], array.get(i));
        }
    }

    @Test
    void removeFromBeginning() {
        IArray<Integer> array = new SingleArray<>(0);
        for (int i = 0; i < 10; i++) {
            array.add(i, i);
        }


        Integer[] expected = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        Integer el = array.remove(0);
        Assertions.assertEquals(0, el);
        Assertions.assertEquals(expected.length, array.size());
        for (int i = 0; i < expected.length; i++) {
            Assertions.assertEquals(expected[i], array.get(i));
        }
    }

    @Test
    void removeFromEnd() {
        IArray<Integer> array = new SingleArray<>(0);
        for (int i = 0; i < 10; i++) {
            array.add(i, i);
        }
        Integer[] expected = {0, 1, 2, 3, 4, 5, 6, 7, 8};
        Integer el = array.remove(9);
        Assertions.assertEquals(9, el);
        Assertions.assertEquals(expected.length, array.size());
        for (int i = 0; i < expected.length; i++) {
            Assertions.assertEquals(expected[i], array.get(i));
        }
    }

    @Test
    void removeFromMiddle() {
        IArray<Integer> array = new SingleArray<>(0);
        for (int i = 0; i < 10; i++) {
            array.add(i, i);
        }
        Integer[] expected = {0, 1, 2, 3, 4, 6, 7, 8, 9};
        Integer el = array.remove(5);
        Assertions.assertEquals(5, el);
        Assertions.assertEquals(expected.length, array.size());
        for (int i = 0; i < expected.length; i++) {
            Assertions.assertEquals(expected[i], array.get(i));
        }
    }

    @Test
    void removeAll() {
        IArray<Integer> array = new SingleArray<>(0);
        for (int i = 0; i < 10; i++) {
            array.add(i, i);
        }
        for (int i = 0; i < 10; i++) {
            array.remove(0);
        }
        Assertions.assertEquals(0, array.size());
    }
}
