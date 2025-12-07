package ru.otus.danilchenko.algorithms.lesson6;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import ru.otus.danilchenko.algorithms.lesson6.report.SimpleSortingReport;
import ru.otus.danilchenko.algorithms.lesson6.report.SortingReportData;
import ru.otus.danilchenko.algorithms.test.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

public class App implements AutoCloseable {
    private final Logger logger = Logger.getLogger(this.getClass().getName());
    private final SimpleSortingReport report = new SimpleSortingReport(Path.of("./reports/simpleReport.xls"));


    private void bubbleSortTest(Test.TestRunnerParameters parameters) {
        int size = Integer.parseInt(parameters.getInputData()[0]);
        Integer[] arr = Arrays.stream(parameters.getInputData()[1].split(" ")).map(Integer::parseInt).toArray(Integer[]::new);
        Integer[] expected = Arrays.stream(parameters.getExpectedData()[0].split(" ")).map(Integer::parseInt).toArray(Integer[]::new);

        final var metric = new Metric("Bubble sort");
        final var utils = new Utils();
        final var comparator = new CompareWithMetic<>(utils::compare, metric);
        final var swapper = new SwapWithMetrics<Integer>(utils::swap, metric);
        final var bubbleSort = new BubbleSort<Integer>(comparator, swapper);

        final var result = bubbleSort.sort(arr);

        final var metricResult = metric.getMetrics();
        report.addReportData("bubbleSort", parameters.getCasePath().toString(),
                new SortingReportData(size, metricResult.get(CompareWithMetic.TAG), metricResult.get(SwapWithMetrics.TAG)));
        if (!Arrays.deepEquals(expected, result)) {
            parameters.getOut().println(String.format("Test err"));
            return;
        }
        parameters.getOut().println("Test ok");
    }


    private void run(String[] args) {
        final var tests = List.of(
                new Test("Bubble sort",
                        Paths.get("./test_cases/lesson6/sorting-tests/0.random"),
                        0, 5,
                        this::bubbleSortTest
                )
        );
        for (var test : tests) {
            test.run();
        }
    }

    private void anotherRun() throws IOException {
        Workbook workbook = new XSSFWorkbook();

        Sheet sheet = workbook.createSheet("Bubble sort");
        sheet.setColumnWidth(0, 6000);
        sheet.setColumnWidth(1, 4000);

        Row header = sheet.createRow(0);



        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setBorderLeft(BorderStyle.THIN);
        headerStyle.setBorderRight(BorderStyle.THIN);
        headerStyle.setBorderTop(BorderStyle.THIN);
        headerStyle.setBorderBottom(BorderStyle.THIN);
        headerStyle.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
        headerStyle.setFillPattern(FillPatternType.SQUARES);
        headerStyle.setWrapText(true);


        XSSFFont font = ((XSSFWorkbook) workbook).createFont();
        font.setFontName("Arial");
        font.setFontHeightInPoints((short) 16);
        font.setBold(true);
        headerStyle.setFont(font);


        Cell headerCell = header.createCell(0);
        headerCell.setCellValue("Bubble sort bla bla bla ");
        headerCell.setCellStyle(headerStyle);

        header.createCell(1).setCellStyle(headerStyle);
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 1));
//        headerCell = header.createCell(1);
//        headerCell.setCellValue("Age");
//        headerCell.setCellStyle(headerStyle);


        CellStyle style = workbook.createCellStyle();
        style.setWrapText(true);

        Row row = sheet.createRow(2);
        Cell cell = row.createCell(0);
        cell.setCellValue("John Smith");
        cell.setCellStyle(style);

        cell = row.createCell(1);
        cell.setCellValue(20);
        cell.setCellStyle(style);

        File currDir = new File(".");
        String path = currDir.getAbsolutePath();
        String fileLocation = path.substring(0, path.length() - 1) + "temp.xlsx";

        FileOutputStream outputStream = new FileOutputStream(fileLocation);
        workbook.write(outputStream);
        workbook.close();
    }

    @Override
    public void close() throws Exception {
        report.build();
        report.close();
    }

    public static void main(String[] args) {
        try (final App app = new App()) {
            app.run(args);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
