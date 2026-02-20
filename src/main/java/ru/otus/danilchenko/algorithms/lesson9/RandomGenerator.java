package ru.otus.danilchenko.algorithms.lesson9;

import org.apache.commons.math3.optim.InitialGuess;
import ru.otus.danilchenko.algorithms.lesson8.MergeSort;
import ru.otus.danilchenko.algorithms.sort.Utils;

import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.Random;
import java.util.SortedSet;
import java.util.TreeSet;

public class RandomGenerator {
    private final Random random = new Random();
    private final int MIN = 0;
    private final int MAX = 65535;
    private final Utils utils = new Utils();

    void generate(int size, Path inPath, Path outPath) {
        Objects.requireNonNull(inPath);
        Objects.requireNonNull(outPath);
        if (size < 0) {
            throw new RuntimeException("size < 0");
        }
        Integer[] arr = new Integer[size];
        try (Writer writer = Files.newBufferedWriter(inPath)) {
            writer.write(String.format("%d\n", size));
            for (int i = 0; i < size; i++) {
                int value = random.nextInt(MAX - MIN + 1) + MIN;
                arr[i] = value;
                writer.write(String.format("%d ", value));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        var sort = new MergeSort<Integer>(utils::compare, value -> {
        });
        sort.sort(arr);

        try (Writer writer = Files.newBufferedWriter(outPath)) {
            for (var el : arr) {
                writer.write(String.format("%d ", el));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
