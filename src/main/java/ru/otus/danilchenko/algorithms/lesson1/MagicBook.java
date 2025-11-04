package ru.otus.danilchenko.algorithms.lesson1;

public class MagicBook {
    private final int xSize;
    private final int ySize;
    private final Speaker speaker;

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }

    public MagicBook(int xSize, int ySize, Speaker speaker) {
        this.xSize = xSize;
        this.ySize = ySize;
        this.speaker = speaker;
    }

    public interface Secret {
        boolean use(int x, int y);
    }



    public void read(Secret secret) {
        for (int y = 0; y < ySize; y++) {
            for (int x = 0; x < ySize; x++) {
                if (secret.use(x, y)) {
                    speaker.pronounce('#');
                    continue;
                }
                speaker.pronounce('.');
            }
            speaker.pronounce('\n');
        }
    }
}
