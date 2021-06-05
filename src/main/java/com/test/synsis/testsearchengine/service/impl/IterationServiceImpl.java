package com.test.synsis.testsearchengine.service.impl;

import com.test.synsis.testsearchengine.dto.ColumnRawMeasure;
import com.test.synsis.testsearchengine.dto.ReportObject;
import com.test.synsis.testsearchengine.dto.TableGuiModel;
import com.test.synsis.testsearchengine.service.IterationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class IterationServiceImpl implements IterationService {

    @Override
    public void printPivotalReport(ReportObject reportObject) {
        Map<Object, List<ColumnRawMeasure>> reportData = reportObject.getReportData();
        for (Object tableCategory : reportData.keySet()) {
            System.out.print("| " + tableCategory + " | ");
            List<ColumnRawMeasure> columnRawMeasures = reportData.get(tableCategory);
            Set<String> columns = new TreeSet<>();
            columnRawMeasures.stream().map(ColumnRawMeasure::getColumnCategory).collect(Collectors.toCollection(() -> columns));
            Set<String> rows = columnRawMeasures.stream().map(ColumnRawMeasure::getRowCategory).collect(Collectors.toSet());

            for (String column : columns) {
                System.out.print(column + " | ");
            }
            System.out.println();
            System.out.print("| ");

            for (String row : rows) {
                System.out.print(row + " | ");
                for (String column : columns) {
                    System.out.print(retrieveMeasureValue(columnRawMeasures, row, column) + " | ");
                }
                System.out.println();
                System.out.print("| ");
            }
            System.out.println("----------------------------------------------------------------------------------------------------------------------");
        }
    }

    @Override
    public List<TableGuiModel> parseReportObjectToGuiModel(ReportObject reportObject) {
        List<TableGuiModel> tableRows = new ArrayList<>();
        Map<Object, List<ColumnRawMeasure>> reportData = reportObject.getReportData();
        log.info("Iterating through report object");
        for (Object tableCategory : reportData.keySet()) {
            TableGuiModel tableRow = new TableGuiModel();
            tableRow.setTableCategory(tableCategory.toString());
            List<ColumnRawMeasure> columnRawMeasures = reportData.get(tableCategory);
            Set<String> columns = new TreeSet<>();
            columnRawMeasures.stream().map(ColumnRawMeasure::getColumnCategory).collect(Collectors.toCollection(() -> columns));
            Set<String> rows = columnRawMeasures.stream().map(ColumnRawMeasure::getRowCategory).collect(Collectors.toSet());

            //Construct and put first row
            List<String> firstRow = new ArrayList<>();
            firstRow.add(tableCategory.toString());
            firstRow.addAll(columns);
            tableRow.getRowColumnValues().add(firstRow);

            // Retrieve/put rows of current category
            for (String row : rows) {
                List<String> rowOfTable = new ArrayList<>();
                rowOfTable.add(row);
                for (String column : columns) {
                    rowOfTable.add(retrieveMeasureValue(columnRawMeasures, row, column));
                }
                tableRow.getRowColumnValues().add(rowOfTable);
            }
            tableRows.add(tableRow);
        }
        return tableRows;
    }

//    /**
//     * Generates test data for iteration
//     * @return
//     */
//    private static ReportObject getMockReportData() {
//        ReportObject reportObject = new ReportObject();
//
//        String[] countries = {"USA", "Canada"};
//        List<ColumnRawMeasure> usaColumnMeasure = new ArrayList<>();
//        usaColumnMeasure.add(ColumnRawMeasure.builder().columnCategory("2001").rowCategory("Wine").measurement("123,123.00").build());
//        usaColumnMeasure.add(ColumnRawMeasure.builder().columnCategory("2001").rowCategory("Bred").measurement("0.00").build());
//        usaColumnMeasure.add(ColumnRawMeasure.builder().columnCategory("2002").rowCategory("Wine").measurement("555.10").build());
//        usaColumnMeasure.add(ColumnRawMeasure.builder().columnCategory("2002").rowCategory("Bred").measurement("7.00").build());
//        usaColumnMeasure.add(ColumnRawMeasure.builder().columnCategory("2004").rowCategory("Wine").measurement(null).build());
//        usaColumnMeasure.add(ColumnRawMeasure.builder().columnCategory("2004").rowCategory("Bred").measurement(null).build());
//        usaColumnMeasure.add(ColumnRawMeasure.builder().columnCategory("2006").rowCategory("Wine").measurement("4,424.00").build());
//        usaColumnMeasure.add(ColumnRawMeasure.builder().columnCategory("2006").rowCategory("Bred").measurement("7,800.00").build());
//        reportObject.getReportData().put(countries[0], usaColumnMeasure);
//
//        List<ColumnRawMeasure> canadaColumnMeasure = new ArrayList<>();
//        canadaColumnMeasure.add(ColumnRawMeasure.builder().columnCategory("2001").rowCategory("Butter").measurement(null).build());
//        canadaColumnMeasure.add(ColumnRawMeasure.builder().columnCategory("2001").rowCategory("Bred").measurement("2,345.00").build());
//        canadaColumnMeasure.add(ColumnRawMeasure.builder().columnCategory("2002").rowCategory("Butter").measurement("876,234.00").build());
//        canadaColumnMeasure.add(ColumnRawMeasure.builder().columnCategory("2002").rowCategory("Bred").measurement(null).build());
//        canadaColumnMeasure.add(ColumnRawMeasure.builder().columnCategory("2004").rowCategory("Butter").measurement("432.00").build());
//        canadaColumnMeasure.add(ColumnRawMeasure.builder().columnCategory("2004").rowCategory("Bred").measurement("3,456.00").build());
//        canadaColumnMeasure.add(ColumnRawMeasure.builder().columnCategory("2006").rowCategory("Butter").measurement(null).build());
//        canadaColumnMeasure.add(ColumnRawMeasure.builder().columnCategory("2006").rowCategory("Bred").measurement("4,566.50").build());
//        reportObject.getReportData().put(countries[1], canadaColumnMeasure);
//
//        return reportObject;
//    }

    private String retrieveMeasureValue(List<ColumnRawMeasure> columnRawMeasures, String row, String column) {
        for (ColumnRawMeasure columnRawMeasure : columnRawMeasures) {
            if (row.equals(columnRawMeasure.getRowCategory()) && column.equals(columnRawMeasure.getColumnCategory())) {
                return columnRawMeasure.getMeasurement() != null ? columnRawMeasure.getMeasurement() : " ";
            }
        }
        return null;
    }

}
