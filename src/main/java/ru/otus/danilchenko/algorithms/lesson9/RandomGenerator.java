package ru.otus.danilchenko.algorithms.lesson9;

import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.Random;

public class RandomGenerator {
    private final Random random = new Random();
    private final int MIN = 0;
    private final int MAX = 65535;
    void generate(int size, Path path) {
        Objects.requireNonNull(path);
        if(size < 0){
            throw new RuntimeException("size < 0");
        }
        try(Writer writer = Files.newBufferedWriter(path)) {
            for(int i = 0; i < size; i++){
                int value = random.nextInt(MAX - MIN + 1) + MIN;
                writer.write(String.format("%d ", value));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
