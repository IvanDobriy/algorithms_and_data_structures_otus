package ru.otus.danilchenko.algorithms.lesson4.dynamic_arrays;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.logging.Logger;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class FactorArrayTests {
    private Logger logger = Logger.getLogger(this.getClass().getName());

    @Test
    void insertIntoEmptyArray() {
        FactorArray<Integer> array = new FactorArray<>(0);
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
}
