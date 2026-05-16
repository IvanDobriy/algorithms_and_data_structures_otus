package ru.otus.danilchenko.algorithms.lesson21;

import ru.otus.danilchenko.algorithms.lesson21.fxe.FXE;
import ru.otus.danilchenko.algorithms.lesson21.island.Island;
import ru.otus.danilchenko.algorithms.lesson21.peas.Peas;
import ru.otus.danilchenko.algorithms.lesson21.tree.Tree;
import ru.otus.danilchenko.algorithms.test.Test;

import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class App {
    private void islandTest(Test.TestRunnerParameters parameters) {
        final Island island = new Island();
        String result = island.calculate(parameters.getInputData());
        if (parameters.getExpectedData()[0].equals(result)) {
            parameters.getOut().println("Test ok");
        } else {
            parameters.getOut().println("Test err, in: " + Arrays.toString(parameters.getInputData()) + " expected: " + Arrays.toString(parameters.getExpectedData()) + " result: " + result);
        }
    }

    private void fxeTest(Test.TestRunnerParameters parameters) {
        final FXE fxe = new FXE();
        String result = fxe.calculate(parameters.getInputData()[0]);
        if (parameters.getExpectedData()[0].equals(result)) {
            parameters.getOut().println("Test ok");
        } else {
            parameters.getOut().println("Test err, in: " + Arrays.toString(parameters.getInputData()) + " expected: " + Arrays.toString(parameters.getExpectedData()) + " result: " + result);
        }
    }

    private void treeTest(Test.TestRunnerParameters parameters) {
        final Tree tree = new Tree();
        String result = tree.calculate(parameters.getInputData());
        if (parameters.getExpectedData()[0].equals(result)) {
            parameters.getOut().println("Test ok");
        } else {
            parameters.getOut().println("Test err, in: " + Arrays.toString(parameters.getInputData()) + " expected: " + Arrays.toString(parameters.getExpectedData()) + " result: " + result);
        }
    }

    private void peasTest(Test.TestRunnerParameters parameters) {
        final Peas peas = new Peas();
        String result = peas.calculate(parameters.getInputData()[0]);
        if (parameters.getExpectedData()[0].equals(result)) {
            parameters.getOut().println("Test ok");
        } else {
            parameters.getOut().println("Test err, in: " + Arrays.toString(parameters.getInputData()) + " expected: " + Arrays.toString(parameters.getExpectedData()) + " result: " + result);
        }
    }

    private void run(String[] args) {
        final String defaultCaseName = "1";
        final var tests = List.of(
//                new Test(
//                        "peas",
//                        defaultCaseName,
//                        Paths.get("./test_cases/lesson21/peas"),
//                        0, Integer.MAX_VALUE,
//                        this::peasTest
//                ),
//                new Test(
//                        "tree",
//                        defaultCaseName,
//                        Paths.get("./test_cases/lesson21/tree"),
//                        0, Integer.MAX_VALUE,
//                        this::treeTest
//                ),
//                new Test(
//                        "fxe",
//                        defaultCaseName,
//                        Paths.get("./test_cases/lesson21/fxe"),
//                        0, Integer.MAX_VALUE,
//                        this::fxeTest
//                )
                new Test(
                        "island",
                        defaultCaseName,
                        Paths.get("./test_cases/lesson21/island"),
                        0, Integer.MAX_VALUE,
                        this::islandTest
                )
        );

        for (var test : tests) {
            test.run();
        }
    }

    public static void main(String[] args) {
        final App app = new App();
        app.run(args);
    }
}
