package ru.otus.danilchenko.algorithms.lesson5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BitOnesCalculatorsTests {
    @Test
    void positiveTest() {
        final long value = -2260560722335367168L;
        final long expected = 8;
        final long result = BitOnesCalculators.simpleCalculation(value);
        Assertions.assertEquals(expected, result);
    }
}
