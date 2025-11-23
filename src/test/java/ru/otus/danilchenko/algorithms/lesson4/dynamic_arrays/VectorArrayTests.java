package ru.otus.danilchenko.algorithms.lesson4.dynamic_arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class VectorArrayTests {
    @Test
    void insertIntoEmptyArray() {
        IArray<Integer> array = new VectorArray<>(0);
        array.add(1, 0);
        Integer[] expected = new Integer[]{1};
        Assertions.assertEquals(expected.length, array.size());
        for (int i = 0; i < expected.length; i++) {
            Assertions.assertEquals(expected[i], array.get(i));
        }
    }

    @Test
    void insertIntoBeginOfArray() {
        IArray<Integer> array = new VectorArray<>(0);
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
        IArray<Integer> array = new VectorArray<>(0);
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
        IArray<Integer> array = new VectorArray<>(0);
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
        IArray<Integer> array = new VectorArray<>(0);
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
        IArray<Integer> array = new VectorArray<>(0);
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



}
