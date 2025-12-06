package ru.otus.danilchenko.algorithms.test;

import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Test {
    private final String testName;
    private final TestRunner testRunner;
    private final Path testCasesPath;
    private final int initialCaseNumber;
    private final int numberOfCases;
    private final PrintStream out = System.out;

    public class TestRunnerParameters {
        private final String[] inputData;
        private final String[] expectedData;
        private final PrintStream out;
        private final Path casePath;

        public TestRunnerParameters(String[] inputData, String[] expectedData, PrintStream out, Path casePath) {
            this.inputData = inputData;
            this.expectedData = expectedData;
            this.out = out;
            this.casePath = casePath;
        }

        public String[] getInputData() {
            return inputData;
        }

        public String[] getExpectedData() {
            return expectedData;
        }

        public PrintStream getOut() {
            return out;
        }

        public Path getCasePath() {
            return casePath;
        }
    }

    public interface TestRunner {
        void run(TestRunnerParameters parameters);
    }

    private String[] getTestCaseData(Path testCasePath) {
        try {
            List<String> lines = Files.readAllLines(testCasePath);
            if (lines.isEmpty()) {
                throw new RuntimeException(String.format("test case file: %s is empty", testCasePath));
            }
            return lines.toArray(String[]::new);
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    private Path getInputTestCasePath(int caseNumber) {
        return testCasesPath.resolve(Paths.get(String.format("test.%s.in", caseNumber)));
    }

    private Path getOutputTestCasePath(int caseNumber) {
        return testCasesPath.resolve(Paths.get(String.format("test.%s.out", caseNumber)));
    }


    private boolean testCaseFileExists(Path testCasesPath) {
        return Files.exists(testCasesPath) && Files.isRegularFile(testCasesPath);
    }

    public Test(String testName, Path testCasesPath, int initialCaseNumber, int numberOfCases, TestRunner testRunner) {
        Objects.requireNonNull(testName);
        Objects.requireNonNull(testRunner);
        Objects.requireNonNull(testCasesPath);
        this.testName = testName;
        this.testRunner = testRunner;
        this.testCasesPath = testCasesPath;
        this.initialCaseNumber = initialCaseNumber;
        this.numberOfCases = numberOfCases;
    }


    public void run() {
        Path inputTestCasePath;
        Path outputTestCasePath;
        out.println(String.format("############################## Test: %s ##############################", testName));
        for (int caseNumber = initialCaseNumber; caseNumber < numberOfCases; caseNumber++) {
            long beforeExecutingTestTimeStamp = System.nanoTime();
            try {
                inputTestCasePath = getInputTestCasePath(caseNumber);
                outputTestCasePath = getOutputTestCasePath(caseNumber);
                if (!(testCaseFileExists(inputTestCasePath) && testCaseFileExists(outputTestCasePath))) {
                    return;
                }
                out.println(String.format("======================= test case: %s ====================", caseNumber));
                testRunner.run(new TestRunnerParameters(getTestCaseData(inputTestCasePath), getTestCaseData(outputTestCasePath), out, testCasesPath));
            } catch (Throwable e) {
                out.println(String.format("Unexpected exception: %s, stack trace: %s", e.getMessage(), Arrays.toString(e.getStackTrace())));
            } finally {
                long afterExecutingTestTimeStamp = System.nanoTime();
                out.println(String.format("Execution time: %d ns", afterExecutingTestTimeStamp - beforeExecutingTestTimeStamp));
            }
        }
    }
}
