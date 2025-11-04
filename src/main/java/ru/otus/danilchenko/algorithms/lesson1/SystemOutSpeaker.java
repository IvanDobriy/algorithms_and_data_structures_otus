package ru.otus.danilchenko.algorithms.lesson1;

public class SystemOutSpeaker implements Speaker {
    @Override
    public void pronounce(char letter) {
        System.out.print(letter);
    }
}
