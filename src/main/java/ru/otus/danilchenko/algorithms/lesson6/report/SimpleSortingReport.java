package ru.otus.danilchenko.algorithms.lesson6.report;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.logging.Logger;

public class SimpleSortingReport implements AutoCloseable {
    private final Logger logger = Logger.getLogger(this.getClass().getName());
    private final Workbook workbook;
    private final Path path;
    private final Map<String, Map<String, List<SortingReportData>>> reports;
    private final CellStyle headerStyle;
    private final CellStyle namesStyle;
    private final CellStyle valuesStyle;
    private final XSSFFont font;
    private final Map<Sheet, Integer> nextTablePosition;
    private final static  int COLUMN_SIZE = 4000;


    private void prepareHeaderCellStyle() {
        headerStyle.setBorderLeft(BorderStyle.THIN);
        headerStyle.setBorderRight(BorderStyle.THIN);
        headerStyle.setBorderTop(BorderStyle.THIN);
        headerStyle.setBorderBottom(BorderStyle.THIN);
        headerStyle.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
        headerStyle.setFillPattern(FillPatternType.SQUARES);
        headerStyle.setFont(font);
        headerStyle.setAlignment(HorizontalAlignment.CENTER);
        headerStyle.setWrapText(true);
    }

    private void prepareNamesCellStyle() {
        namesStyle.setBorderLeft(BorderStyle.THIN);
        namesStyle.setBorderRight(BorderStyle.THIN);
        namesStyle.setBorderTop(BorderStyle.THIN);
        namesStyle.setBorderBottom(BorderStyle.THIN);
        namesStyle.setFillForegroundColor(IndexedColors.AQUA.getIndex());
        namesStyle.setFillPattern(FillPatternType.SQUARES);
        namesStyle.setFont(font);
        namesStyle.setAlignment(HorizontalAlignment.RIGHT);
        namesStyle.setWrapText(true);
    }

    private void prepareValuesCellStyle() {
        valuesStyle.setBorderLeft(BorderStyle.THIN);
        valuesStyle.setBorderRight(BorderStyle.THIN);
        valuesStyle.setBorderTop(BorderStyle.THIN);
        valuesStyle.setBorderBottom(BorderStyle.THIN);
        valuesStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());
        valuesStyle.setFillPattern(FillPatternType.SQUARES);
        valuesStyle.setFont(font);
        valuesStyle.setAlignment(HorizontalAlignment.CENTER);
        valuesStyle.setWrapText(true);
    }


    private void prepareCellFont() {
        font.setFontName("Arial");
        font.setFontHeightInPoints((short) 16);
        font.setBold(true);
    }

    public SimpleSortingReport(Path path) {
        Objects.requireNonNull(path);
        this.path = path;
        nextTablePosition = new HashMap<>();
        workbook = new XSSFWorkbook();
        reports = new HashMap<>();
        headerStyle = workbook.createCellStyle();
        namesStyle = workbook.createCellStyle();
        valuesStyle = workbook.createCellStyle();
        font = ((XSSFWorkbook) workbook).createFont();
        prepareCellFont();
        prepareHeaderCellStyle();
        prepareNamesCellStyle();
        prepareValuesCellStyle();
    }

    private Sheet createSheet(String name) {
        final Sheet sheet = workbook.createSheet(name);
        return sheet;
    }

    private void createTableNameHeader(String name, Sheet sheet, int size, int index) {
        Row header = sheet.createRow(index);
        int position = 0;
        Cell headerCell = header.createCell(position++);
        headerCell.setCellStyle(headerStyle);
        headerCell.setCellValue(name);
        for (int i = position; i < size; i++) {
            header.createCell(i).setCellStyle(headerStyle);
        }
        sheet.addMergedRegion(new CellRangeAddress(index, index, 0, size));
    }


    private interface Callback {
        void run(SortingReportData sortingReportData, Cell cell);
    }

    private void createRow(Sheet sheet, int index, String name, List<SortingReportData> reportDataList, Callback callback) {
        Row row = sheet.createRow(index);
        int position = 0;
        sheet.setColumnWidth(position, COLUMN_SIZE);
        Cell nameCell = row.createCell(position++);
        nameCell.setCellValue(name);
        nameCell.setCellStyle(namesStyle);
        for (var reportData : reportDataList) {
            Cell cell = row.createCell(position++);
            callback.run(reportData, cell);
            sheet.setColumnWidth(position, COLUMN_SIZE);
        }
    }

    private void createExchangeRow(Sheet sheet, List<SortingReportData> reportDataList, int index) {
        createRow(sheet, index, "Обмены: ", reportDataList, (reportData, cell) -> {
            cell.setCellStyle(valuesStyle);
            cell.setCellValue(reportData.getNumberOfExchanges());
        });
    }

    private void createComparisonsRow(Sheet sheet, List<SortingReportData> reportDataList, int index) {
        createRow(sheet, index, "Сравнения: ", reportDataList, (reportData, cell) -> {
            cell.setCellStyle(valuesStyle);
            cell.setCellValue(reportData.getNumberOfComparisons());
        });
    }

    private void createArrSizeRow(Sheet sheet, List<SortingReportData> reportDataList, int index) {
        createRow(sheet, index, "Размер: ", reportDataList, (reportData, cell) -> {
            cell.setCellStyle(valuesStyle);
            cell.setCellValue(reportData.getArraySize());
        });
    }


    private void createTable(Sheet sheet, String tableName, List<SortingReportData> reportDataList) {
        int rowPosition = nextTablePosition.getOrDefault(sheet, 0);
        createTableNameHeader(tableName, sheet, reportDataList.size(), rowPosition++);
        createArrSizeRow(sheet, reportDataList, rowPosition++);
        createExchangeRow(sheet, reportDataList, rowPosition++);
        createComparisonsRow(sheet, reportDataList, rowPosition++);
        nextTablePosition.put(sheet, rowPosition + 1);
    }

    public void addReportData(String sheetName, String testCase, SortingReportData sortingReportData) {
        Objects.requireNonNull(sheetName);
        Objects.requireNonNull(testCase);
        Objects.requireNonNull(sortingReportData);
        Map<String, List<SortingReportData>> sheet = reports.getOrDefault(sheetName, new HashMap<>());
        List<SortingReportData> tableData = sheet.getOrDefault(testCase, new ArrayList<>());
        tableData.add(sortingReportData);
        sheet.put(testCase, tableData);
        reports.put(sheetName, sheet);
    }

    public void build() {
        for (var sheetData : reports.entrySet()) {
            //prepare sheet
            logger.info(String.format("Sheet name: %s", sheetData.getKey()));
            Sheet sheet = createSheet(sheetData.getKey());
            for (var table : sheetData.getValue().entrySet()) {
                logger.info(String.format("Table name: %s", table.getKey()));
                createTable(sheet, table.getKey(), table.getValue());
            }
        }
    }

    @Override
    public void close() throws Exception {
        try (OutputStream outputStream = Files.newOutputStream(path)) {
            workbook.write(outputStream);
        } finally {
            workbook.close();
        }
    }
}
