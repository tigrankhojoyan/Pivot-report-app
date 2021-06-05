package com.test.synsis.testsearchengine.service;

import com.test.synsis.testsearchengine.dto.ReportObject;

/**
 * Class intended to retrieve report data from database
 */
public interface QueryService {

    /**
     * Retrieves appropriate data for report according to parameters
     */
    ReportObject retrieveReportData(String tableCategory, String columnCategory, String rowCategory, String measure);

}
