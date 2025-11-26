package ru.otus.danilchenko.algorithms.lesson4;

import ru.otus.danilchenko.algorithms.lesson4.dynamic_arrays.*;

import java.util.ArrayList;
import java.util.Map;

public class App {
    private interface Callback {
        void run();
    }

    private void measure(String description, Callback callback) {
        System.out.println(description);
        long before = System.nanoTime();
        callback.run();
        System.out.println(String.format("execution time: %s", System.nanoTime() - before));
    }

    private <T> Map<String, IArray<T>> getArrays() {
        return Map.of(
                SingleArray.class.getName(), new SingleArray<>(0),
                VectorArray.class.getName(), new VectorArray<>(0),
                FactorArray.class.getName(), new FactorArray<>(0),
                MatrixArray.class.getName(), new MatrixArray<>(0),
                ArrayListWrapper.class.getName(), new ArrayListWrapper<>(new ArrayList<>(0))
        );
    }

    private void checkInsertionToBeginning(int max) {
        Map<String, IArray<Integer>> arrays = getArrays();
        for (var entry : arrays.entrySet()) {
            measure(String.format("for: %s, check insertion to beginning, max: %s", entry.getKey(), max), () -> {
                IArray<Integer> array = entry.getValue();
                for (int i = 0; i < max; i++) {
                    array.add(i, 0);
                }
            });
        }
    }

    private void checkInsertionToMiddle(int max) {
        Map<String, IArray<Integer>> arrays = getArrays();
        for (var entry : arrays.entrySet()) {
            measure(String.format("for: %s, check insertion to middle , max: %s", entry.getKey(), max), () -> {
                IArray<Integer> array = entry.getValue();
                for (int i = 0; i < max; i++) {
                    array.add(i, i / 2);
                }
            });
        }
    }

    private void checkInsertionToEnd(int max) {
        Map<String, IArray<Integer>> arrays = getArrays();
        for (var entry : arrays.entrySet()) {
            measure(String.format("for: %s, check insertion to end , max: %s", entry.getKey(), max), () -> {
                IArray<Integer> array = entry.getValue();
                for (int i = 0; i < max; i++) {
                    array.add(i, i);
                }
            });
        }
    }

    private void removeFromBeginning(int max) {
        Map<String, IArray<Integer>> arrays = getArrays();
        for (var entry : arrays.entrySet()) {
            IArray<Integer> array = entry.getValue();
            for (int i = 0; i < max; i++) {
                array.add(i, i);
            }
            measure(String.format("for: %s, check  removing from beginning, max: %s", entry.getKey(), max), () -> {
                for (int i = max - 1; i >= 0; i--) {
                    array.remove(0);
                }
            });
        }
    }

    private void removeFromMiddle(int max) {
        Map<String, IArray<Integer>> arrays = getArrays();
        for (var entry : arrays.entrySet()) {
            IArray<Integer> array = entry.getValue();
            for (int i = 0; i < max; i++) {
                array.add(i, i);
            }
            measure(String.format("for: %s, check  removing from middle, max: %s", entry.getKey(), max), () -> {
                for (int i = max - 1; i >= 0; i--) {
                    array.remove(i / 2);
                }
            });
        }
    }

    private void removeFromEnd(int max) {
        Map<String, IArray<Integer>> arrays = getArrays();
        for (var entry : arrays.entrySet()) {
            IArray<Integer> array = entry.getValue();
            for (int i = 0; i < max; i++) {
                array.add(i, i);
            }
            measure(String.format("for: %s, check removing from beginning, max: %s", entry.getKey(), max), () -> {
                for (int i = max - 1; i >= 0; i--) {
                    array.remove(i);
                }
            });
        }
    }


    private void run() {
//        for (int i = 100; i <= 10_000; i *= 10) {
//            System.out.println("=============================================================================");
//            checkInsertionToBeginning(i);
//            System.out.println("=============================================================================");
//            checkInsertionToMiddle(i);
//            System.out.println("=============================================================================");
//            checkInsertionToEnd(i);
//            System.out.println("=============================================================================");
//        }

        for (int i = 100; i <= 10_000; i *= 10) {
            System.out.println("=============================================================================");
            removeFromBeginning(i);
            System.out.println("=============================================================================");
            removeFromMiddle(i);
            System.out.println("=============================================================================");
            removeFromEnd(i);
            System.out.println("=============================================================================");
        }
    }

    public static void main(String[] args) {
        App app = new App();
        app.run();
    }
}
