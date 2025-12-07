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
    private final XSSFFont font;
    private final Map<Sheet, Integer> nextTablePosition;

    private void prepareHeaderCellStyle() {
        headerStyle.setBorderLeft(BorderStyle.THIN);
        headerStyle.setBorderRight(BorderStyle.THIN);
        headerStyle.setBorderTop(BorderStyle.THIN);
        headerStyle.setBorderBottom(BorderStyle.THIN);
        headerStyle.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
        headerStyle.setFillPattern(FillPatternType.SQUARES);
        headerStyle.setFont(font);
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
        font = ((XSSFWorkbook) workbook).createFont();
        prepareCellFont();
        prepareHeaderCellStyle();
    }

    private Sheet createSheet(String name) {
        final Sheet sheet = workbook.createSheet(name);
        return sheet;
    }

    private void createTableNameHeader(String name, Sheet sheet, int index) {
        Row header = sheet.createRow(index);
        Cell headerCell = header.createCell(0);
        headerCell.setCellStyle(headerStyle);
        headerCell.setCellValue(name);
        header.createCell(1);
        sheet.addMergedRegion(new CellRangeAddress(index, index, 0, 1));
    }

    private void createTable(Sheet sheet, String tableName, List<SortingReportData> reportDataList) {
        int rowPosition = nextTablePosition.getOrDefault(sheet, 0);
        nextTablePosition.put(sheet, rowPosition + 6);
        createTableNameHeader(tableName, sheet, rowPosition + 1);
    }

    public void addReportData(String sheetName, String testCase, SortingReportData sortingReportData) {
        Objects.requireNonNull(sheetName);
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
