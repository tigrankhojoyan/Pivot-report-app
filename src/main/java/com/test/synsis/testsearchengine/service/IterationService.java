package com.test.synsis.testsearchengine.service;

import com.test.synsis.testsearchengine.dto.ReportObject;
import com.test.synsis.testsearchengine.dto.TableGuiModel;

import java.util.List;

public interface IterationService {

    /**
     * Prints report into console(for test purpose)
     *
     * @param reportObject
     */
    void printPivotalReport(ReportObject reportObject);

    /**
     * Parses report data to gui model to generate html report tables
     *
     * @param reportObject
     * @return
     */
    List<TableGuiModel> parseReportObjectToGuiModel(ReportObject reportObject);
}
