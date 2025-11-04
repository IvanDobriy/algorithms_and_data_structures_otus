package ru.otus.danilchenko.algorithms.lesson1;

import java.util.Arrays;
import java.util.List;

public class App {
    public static void main(String[] args) {
        Speaker speaker = new SystemOutSpeaker();
        List<MagicBook.Secret> secrets = List.of(
                Secrets::secret1,
                Secrets::secret2,
                Secrets::secret3,
                Secrets::secret4,
                Secrets::secret5,
                Secrets::secret6,
                Secrets::secret7,
                Secrets::secret8,
                Secrets::secret9,
                Secrets::secret10,
                Secrets::secret11,
                Secrets::secret12,
                Secrets::secret13,
                Secrets::secret14,
                Secrets::secret15,
                Secrets::secret16,
                Secrets::secret17,
                Secrets::secret18,
                Secrets::secret19,
                Secrets::secret20,
                Secrets::secret21,
                Secrets::secret22,
                Secrets::secret23,
                Secrets::secret24,
                Secrets::secret25
        );
        MagicBook magicBook = new MagicBook(speaker);
        int secretNumber = 1;
        int notDoneCounter = 0;
        for (var secret : secrets) {
            try {
                System.out.println(String.format("-------------------------- secret: %d --------------------------", secretNumber));
                magicBook.read(secret);
            } catch (Exception e) {
                System.out.println(String.format("some exception: %s, stack trace: %s", e.getMessage(), Arrays.toString(e.getStackTrace())));
                notDoneCounter++;
            } finally {
                secretNumber++;
            }
        }
        System.out.println("============================ RESULT ===============================");
        System.out.println(String.format("done: %d", secrets.size() - notDoneCounter));
        System.out.println(String.format("NOT done: %d <---- :)", notDoneCounter));
        System.out.println("===================================================================");
    }
}
