package ru.otus.danilchenko.algorithms.lesson6.report;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.nio.file.Path;
import java.util.*;

public class SimpleSortingReport implements AutoCloseable {
    private final Workbook workbook;
    private final Path path;
    private final Map<String, List<SortingReportData>> reports;

    public SimpleSortingReport(Path path) {
        Objects.requireNonNull(path);
        this.path = path;
        workbook = new XSSFWorkbook();
        reports = new HashMap<>();
    }

    public void addReportData(String sheetName, SortingReportData sortingReportData) {
        Objects.requireNonNull(sheetName);
        Objects.requireNonNull(sortingReportData);
        List<SortingReportData> reportDataList = reports.getOrDefault(sheetName, new ArrayList<>());
        reportDataList.add(sortingReportData);
        reports.put(sheetName, reportDataList);
    }

    public void build() {
        for (var report : reports.entrySet()) {
            //prepare sheet
            for (var reportData : report.getValue()) {
                //prepare cells
            }
        }
    }

    @Override
    public void close() throws Exception {
        workbook.close();
    }
}
