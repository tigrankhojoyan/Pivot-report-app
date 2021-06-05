package com.test.synsis.testsearchengine.service.impl;

import com.test.synsis.testsearchengine.dto.TableGuiModel;
import com.test.synsis.testsearchengine.service.HtmlReportGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class HtmlReportGeneratorImpl implements HtmlReportGenerator {

    private static final String HTML_REPORT_START = "<!DOCTYPE html>\n" +
            "<html>\n" +
            "<head>\n" +
            "<style>\n" +
            "table, th, td {\n" +
            "  border: 1px solid black;\n" +
            "  border-collapse: collapse;\n" +
            "}\n" +
            "</style>\n" +
            "</head>" +
            "<body>\n" +
            "\n" +
            "<h1>Pivotal report</h1>";
    private static final String HTML_REPORT_END = "</body>\n" +
            "</html>";
    private static final String DATE_TIME_FORMAT_TEMPLATE = "dd-M-yyyy-hh_mm_ss";
    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat(DATE_TIME_FORMAT_TEMPLATE);


    /**
     * non-Javadoc
     *
     * @see HtmlReportGenerator#generateHTMLReport(List)
     */
    @Override
    public File generateHTMLReport(List<TableGuiModel> guiModelList) {
        log.info("Generating html report to in project/reports directory");
        try {
            Files.createDirectories(Paths.get("..", "pivotal_reports"));
        } catch (IOException e) {
            log.error("Failed to create directory for reports");
            throw new RuntimeException(e);
        }
        Path path = Paths.get("..", "pivotal_reports", "PivotalReport" + SIMPLE_DATE_FORMAT.format(new Date()) + "_.html");
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            writer.write(HTML_REPORT_START);
            for (TableGuiModel tableGuiModel : guiModelList) {
                writer.write("<table style=\"width:100%\">");
                List<List<String>> rowColumnValues = tableGuiModel.getRowColumnValues();
                for (List<String> rows : rowColumnValues) {
                    writer.write("<tr>");
                    for (String column : rows) {
                        writer.write("<td>" + (StringUtils.hasText(column) ? column : " ") + "</td>");
                    }
                    writer.write("/<tr>");
                }
                writer.write("/<table>");
            }
            writer.write(HTML_REPORT_END);
        } catch (IOException e) {
            log.error("Failed to write data to file", e);
        }
        return path.toFile();
    }
}
