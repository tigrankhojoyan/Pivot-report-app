package com.test.synsis.testsearchengine.service.impl;

import com.test.synsis.testsearchengine.dto.Category;
import com.test.synsis.testsearchengine.dto.ColumnRawMeasure;
import com.test.synsis.testsearchengine.dto.ReportObject;
import com.test.synsis.testsearchengine.model.Sales;
import com.test.synsis.testsearchengine.model.SalesKey;
import com.test.synsis.testsearchengine.repository.SalesRepository;
import com.test.synsis.testsearchengine.service.QueryService;
import com.test.synsis.testsearchengine.service.RepoFactory;
import com.test.synsis.testsearchengine.service.ValidationService;
import com.test.synsis.testsearchengine.util.ReflectionUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@AllArgsConstructor
@Service
public class QueryServiceImpl implements QueryService {

    private final RepoFactory repoFactory;
    private final SalesRepository salesRepository;
    private final ValidationService validationService;

    /**
     * non-Javadoc
     *
     * @see QueryService#retrieveReportData(String, String, String, String)
     */
    @Override
    public ReportObject retrieveReportData(String tableCategory, String columnCategory, String rowCategory, String measure) {
        validationService.validateReportGenerationInput(tableCategory, columnCategory, rowCategory, measure);
        ReportObject reportObject = new ReportObject();
        List tableRecords = repoFactory.getAllRecordsByCategory(Category.findCategoryByName(tableCategory));
        for (Object tableRecord : tableRecords) {
            String tableCategoryName = (String) ReflectionUtils.invokeGetter(tableRecord, "name");
            reportObject.getReportData().put(tableCategoryName, new ArrayList<>());
            List columnRecords = repoFactory.getAllRecordsByCategory(Category.findCategoryByName(columnCategory));
            for (Object columnRecord : columnRecords) {
                String columnName = (String) ReflectionUtils.invokeGetter(columnRecord, "name");
                SalesKey salesKey = new SalesKey();
                ReflectionUtils.invokeSetter(salesKey, Category.findCategoryByName(tableCategory).toString().toLowerCase() + "Id", tableRecord);
                ReflectionUtils.invokeSetter(salesKey, Category.findCategoryByName(columnCategory).toString().toLowerCase() + "Id", columnRecord);
                Sales sales = Sales.builder().id(salesKey).build();
                List<Sales> salesList = salesRepository.findAll(Example.of(sales));
                for (Sales salesPersisted : salesList) {
                    SalesKey key = salesPersisted.getId();
                    Object rowCategoryObject = ReflectionUtils.invokeGetter(key, rowCategory.toLowerCase() + "Id");
                    Object measureObject = ReflectionUtils.invokeGetter(salesPersisted, measure);
                    String rawName = rowCategoryObject != null ? (String) ReflectionUtils.invokeGetter(rowCategoryObject, "name") : "";

                    ColumnRawMeasure columnRawMeasure = ColumnRawMeasure.builder().columnCategory(columnName).rowCategory(rawName)
                            .measurement(measureObject != null ? measureObject.toString() : "").build();

                    reportObject.getReportData().get(tableCategoryName).add(columnRawMeasure);
                }
            }
        }

        return reportObject;
    }

}
