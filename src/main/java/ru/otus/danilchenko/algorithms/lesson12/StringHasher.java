package ru.otus.danilchenko.algorithms.lesson12;

import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class StringHasher implements IHasher<String> {
    private final Crc16 crc;

    public StringHasher(Crc16 crc) {
        Objects.requireNonNull(crc);
        this.crc = crc;
    }

    @Override
    public long execute(String key) {
        byte[] rawData = key.getBytes(StandardCharsets.UTF_8);
        return crc.calculate(rawData);
    }
}
