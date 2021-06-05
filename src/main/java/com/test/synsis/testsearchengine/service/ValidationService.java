package com.test.synsis.testsearchengine.service;

/**
 * Class intended to validate input data
 */
public interface ValidationService {
    /**
     * Checks report generation input parameters
     *
     * @param tableCategory  - category of table
     * @param columnCategory - category of column
     * @param rowCategory    - row category
     * @param measure        - measure param
     */
    void validateReportGenerationInput(String tableCategory, String columnCategory, String rowCategory, String measure);
}
