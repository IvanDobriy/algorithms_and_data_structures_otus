package ru.otus.danilchenko.algorithms.report;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class TreeReport implements AutoCloseable {
    private final Logger logger = Logger.getLogger(this.getClass().getName());
    private final Workbook workbook;
    private final Path path;
    private final Map<String, Map<String, List<TreeReportData>>> reports;
    private final CellStyle headerStyle;
    private final CellStyle namesStyle;
    private final CellStyle valuesStyle;
    private final XSSFFont font;
    private final Map<Sheet, Integer> nextTablePosition;
    private final static int COLUMN_SIZE = 10000;


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


    private Sheet createSheet(String name) {
        return workbook.createSheet(name);
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
        void run(TreeReportData reportData, Cell cell);
    }

    private void createRow(Sheet sheet, int index, String name, List<TreeReportData> reportDataList, Callback callback) {
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

    private void createInsertExecutionTimeRow(Sheet sheet, List<TreeReportData> reportDataList, int index) {
        createRow(sheet, index, ": ", reportDataList, (reportData, cell) -> {
            cell.setCellStyle(valuesStyle);
            cell.setCellValue(reportData.getInsertExecutionTime());
        });
    }

    private void createRemoveExecutionTimeRow(Sheet sheet, List<TreeReportData> reportDataList, int index) {
        createRow(sheet, index, ": ", reportDataList, (reportData, cell) -> {
            cell.setCellStyle(valuesStyle);
            cell.setCellValue(reportData.getRemoveExecutionTime());
        });
    }

    private void createSearchExecutionTimeRow(Sheet sheet, List<TreeReportData> reportDataList, int index) {
        createRow(sheet, index, ": ", reportDataList, (reportData, cell) -> {
            cell.setCellStyle(valuesStyle);
            cell.setCellValue(reportData.getRemoveExecutionTime());
        });
    }

    private void createTable(Sheet sheet, String tableName, List<TreeReportData> reportDataList) {
        int rowPosition = nextTablePosition.getOrDefault(sheet, 0);
        createTableNameHeader(tableName, sheet, reportDataList.size(), rowPosition++);
        createInsertExecutionTimeRow(sheet, reportDataList, rowPosition++);
        createRemoveExecutionTimeRow(sheet, reportDataList, rowPosition++);
        createSearchExecutionTimeRow(sheet, reportDataList, rowPosition++);
        nextTablePosition.put(sheet, rowPosition + 1);
    }

    public TreeReport(Path path) {
        Objects.requireNonNull(path);
        this.path = path;
        nextTablePosition = new HashMap<>();
        workbook = new XSSFWorkbook();
        reports = new HashMap<>();
        headerStyle = workbook.createCellStyle();
        namesStyle = workbook.createCellStyle();
        valuesStyle = workbook.createCellStyle();
        font = ((XSSFWorkbook) workbook).createFont();
    }

    public void addReportData(String sheetName, String testCase, TreeReportData treeReportData) {
        Objects.requireNonNull(sheetName);
        Objects.requireNonNull(testCase);
        Objects.requireNonNull(treeReportData);
        Map<String, List<TreeReportData>> sheet = reports.getOrDefault(sheetName, new HashMap<>());
        List<TreeReportData> tableData = sheet.getOrDefault(testCase, new ArrayList<>());
        tableData.add(treeReportData);
        sheet.put(testCase, tableData);
        reports.put(sheetName, sheet);
    }

    public void build() {
        final var sortedReports = reports.entrySet().stream().sorted(Map.Entry.comparingByKey()).collect(Collectors.toList());
        for (var sheetData : sortedReports) {
            //prepare sheet
            logger.info(String.format("Sheet name: %s", sheetData.getKey()));
            Sheet sheet = createSheet(sheetData.getKey());
            final var sortedTables = sheetData.getValue().entrySet().stream().sorted(Map.Entry.comparingByKey()).collect(Collectors.toList());
            for (var table : sortedTables) {
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
