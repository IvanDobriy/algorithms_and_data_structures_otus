package ru.otus.danilchenko.algorithms.lesson6.report;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.nio.file.Path;
import java.util.*;
import java.util.logging.Logger;

public class SimpleSortingReport implements AutoCloseable {
    private final Logger logger = Logger.getLogger(this.getClass().getName());
    private final Workbook workbook;
    private final Path path;
    private final Map<String, Map<String, List<SortingReportData>>> reports;

    public SimpleSortingReport(Path path) {
        Objects.requireNonNull(path);
        this.path = path;
        workbook = new XSSFWorkbook();
        reports = new HashMap<>();
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
        for (var sheet : reports.entrySet()) {
            //prepare sheet
            logger.info(String.format("Sheet name: %s", sheet.getKey()));
            for (var table : sheet.getValue().entrySet()) {
                logger.info(String.format("Table name: %s", table.getKey()));
                for(var data: table.getValue()){
                    logger.info(String.format("Data: %s", data));
                }
            }
        }
    }

    @Override
    public void close() throws Exception {
        workbook.close();
    }
}
