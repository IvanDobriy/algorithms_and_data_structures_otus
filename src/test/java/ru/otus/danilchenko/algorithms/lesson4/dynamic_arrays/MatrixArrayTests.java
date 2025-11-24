package ru.otus.danilchenko.algorithms.lesson4.dynamic_arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MatrixArrayTests {
    @Test
    void insertIntoEmptyArray() {
        IArray<Integer> array = new MatrixArray<>(0);
        array.add(1, 0);
        Integer[] expected = new Integer[]{1};
        Assertions.assertEquals(expected.length, array.size());
        for (int i = 0; i < expected.length; i++) {
            Assertions.assertEquals(expected[i], array.get(i));
        }
    }
}
