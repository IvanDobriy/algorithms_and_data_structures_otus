package ru.otus.danilchenko.algorithms.lesson6;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import ru.otus.danilchenko.algorithms.test.Test;

import java.io.*;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

public class App {
    private final Logger logger = Logger.getLogger(this.getClass().getName());

    private void bubbleSortTest(String[] inputData, String[] expectedData, PrintStream out) {
        Integer[] arr = Arrays.stream(inputData[1].split(" ")).map(Integer::parseInt).toArray(Integer[]::new);
        Integer[] expected = Arrays.stream(expectedData[0].split(" ")).map(Integer::parseInt).toArray(Integer[]::new);

        final var metric = new Metric("bubble sort");
        final var utils = new Utils();
        final var comparator = new CompareWithMetic<>(utils::compare, metric);
        final var swapper = new SwapWithMetrics<Integer>(utils::swap, metric);
        final var bubbleSort = new BubbleSort<Integer>(comparator, swapper);

        final var result = bubbleSort.sort(arr);

        if (!Arrays.deepEquals(expected, result)) {
            out.println(String.format("Test err"));
            return;
        }
        out.println("Test ok");
    }


    private void run(String[] args) {
        final var tests = List.of(
                new Test("Bubble sort",
                        Paths.get("./test_cases/lesson6/sorting-tests/0.random"),
                        0, 5,
                        this::bubbleSortTest
                )
        );
        for(var test: tests) {
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



        XSSFFont font = ((XSSFWorkbook) workbook).createFont();
        font.setFontName("Arial");
        font.setFontHeightInPoints((short) 16);
        font.setBold(true);
        headerStyle.setFont(font);




        Cell headerCell = header.createCell(0);
        headerCell.setCellValue("Bubble sort bla bla bla ");
        headerCell.setCellStyle(headerStyle);

        header.createCell(1).setCellStyle(headerStyle);
        sheet.addMergedRegion(new CellRangeAddress(0,0,0,1));
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

    public static void main(String[] args) throws IOException {
        final App app = new App();
//        app.run(args);
        app.anotherRun();
    }
}
