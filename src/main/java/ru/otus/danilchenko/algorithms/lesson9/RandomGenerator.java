package ru.otus.danilchenko.algorithms.lesson9;

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
    void generate(int size, Path inPath, Path outPath) {
        Objects.requireNonNull(inPath);
        Objects.requireNonNull(outPath);
        if(size < 0){
            throw new RuntimeException("size < 0");
        }
        SortedSet<Integer> set = new TreeSet<>();
        try(Writer writer = Files.newBufferedWriter(inPath)) {
            writer.write(String.format("%d\n", size));
            for(int i = 0; i < size; i++){
                int value = random.nextInt(MAX - MIN + 1) + MIN;
                set.add(value);
                writer.write(String.format("%d ", value));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try(Writer writer = Files.newBufferedWriter(outPath)) {
            for(var el: set){
                writer.write(String.format("%d ", el));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
