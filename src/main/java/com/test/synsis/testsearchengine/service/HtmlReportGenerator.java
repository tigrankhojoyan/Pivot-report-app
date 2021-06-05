package com.test.synsis.testsearchengine.service;

import com.test.synsis.testsearchengine.dto.TableGuiModel;

import java.io.File;
import java.util.List;

/**
 * Class intended to generate pivotal report in .html format
 */
public interface HtmlReportGenerator {
    /**
     * Generates .html pivotal report according to input.
     * Report is generated in 'pivotal_reports' folder, which is the same place, where is the project
     */
    File generateHTMLReport(List<TableGuiModel> guiModelList);
}
