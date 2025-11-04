package ru.otus.danilchenko.algorithms.lesson1;

public class App {
    public static void main(String[] args){
        Speaker speaker = new SystemOutSpeaker();
        MagicBook magicBook = new MagicBook(25, 25, speaker);
        magicBook.read(Secrets::secret5);
    }
}
