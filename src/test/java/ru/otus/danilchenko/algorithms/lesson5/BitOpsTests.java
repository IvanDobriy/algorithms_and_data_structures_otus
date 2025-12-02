package ru.otus.danilchenko.algorithms.lesson5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BitOpsTests {
    @Test
    void positiveTest() {
        final long digit  = 0x8000000000000000L;
        final long expected = 0x1000000000000000L;
        final long result = BitOps.rSh(digit, 3);
        Assertions.assertEquals(expected, result);
    }
    @Test
    void someTest() {
        final long digit = -2260560722335367168L;
        final long expected = 8093091675687092224L;
        final long result = BitOps.rSh(digit, 1);
        Assertions.assertEquals(expected, result);
    }
}
