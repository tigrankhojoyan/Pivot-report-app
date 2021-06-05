package com.test.synsis.testsearchengine.service.impl;

import com.test.synsis.testsearchengine.dto.Category;
import com.test.synsis.testsearchengine.dto.Measure;
import com.test.synsis.testsearchengine.exception.BusinessException;
import com.test.synsis.testsearchengine.service.ValidationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @see ValidationService
 */
@Slf4j
@Service
public class ValidationServiceImpl implements ValidationService {


    /**
     * non-Javadoc
     *
     * @see ValidationService#validateReportGenerationInput(String, String, String, String)
     */
    @Override
    public void validateReportGenerationInput(String tableCategory, String columnCategory, String rowCategory, String measure) {
        // Checks for non null/non empty values
        if (!StringUtils.hasText(tableCategory) || !StringUtils.hasText(columnCategory) || !StringUtils.hasText(rowCategory) || !StringUtils.hasText(rowCategory)) {
            throw new BusinessException(String.format("Request parameters should not be null/empty(tableCategory - %s, columnCategory - %s, rowCategory - %s, " +
                    "measure - %s)", tableCategory, columnCategory, rowCategory, measure));
        }
        // Checks that there os no duplicate categories
        if (tableCategory.equalsIgnoreCase(columnCategory) || tableCategory.equalsIgnoreCase(rowCategory) || columnCategory.equalsIgnoreCase(rowCategory)) {
            throw new BusinessException(String.format("Table category, column category and row category must be different tables(tableCategory - %s, columnCategory - %s, " +
                    "rowCategory - %s)", tableCategory, columnCategory, rowCategory));
        }

        // Checks for each parameter
        if (Category.findCategoryByName(tableCategory) == null) {
            throw new BusinessException("Invalid table category (" + tableCategory + ")");
        }
        if (Category.findCategoryByName(columnCategory) == null) {
            throw new BusinessException("Invalid column category (" + columnCategory + ")");
        }
        if (Category.findCategoryByName(rowCategory) == null) {
            throw new BusinessException("Invalid row category (" + rowCategory + ")");
        }
        if (Measure.findMeasureByValue(measure) == null) {
            throw new BusinessException("Invalid measure category (" + measure + ")");
        }
    }
}
