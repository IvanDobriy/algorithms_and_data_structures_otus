package ru.otus.danilchenko.algorithms.lesson1;

public class MagicBook {
    private final int maxSize;
    private final Speaker speaker;

    public MagicBook(Speaker speaker) {
        maxSize = 25;
        this.speaker = speaker;
    }

    public interface Secret {
        boolean use(int x, int y, int maxSize);
    }

    public void read(Secret secret) {
        for (int y = 0; y < maxSize; y++) {
            for (int x = 0; x < maxSize; x++) {
                if (secret.use(x, y, maxSize)) {
                    speaker.pronounce('#');
                    continue;
                }
                speaker.pronounce('.');
            }
            speaker.pronounce('\n');
        }
    }
}
