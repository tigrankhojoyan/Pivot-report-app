package com.test.synsis.testsearchengine.controller;

import com.test.synsis.testsearchengine.dto.ReportObject;
import com.test.synsis.testsearchengine.dto.TableGuiModel;
import com.test.synsis.testsearchengine.service.HtmlReportGenerator;
import com.test.synsis.testsearchengine.service.IterationService;
import com.test.synsis.testsearchengine.service.QueryService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.List;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/htmlgenerate")
public class HtmlReportGeneratorController {

    private final QueryService queryService;
    private final IterationService iterationService;
    private final HtmlReportGenerator htmlReportGenerator;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public String generateHtmlReport(@RequestParam("tableCategory") String tableCategory,
                                     @RequestParam("columnCategory") String columnCategory,
                                     @RequestParam("rowCategory") String rowCategory,
                                     @RequestParam("measure") String measure) {
        log.info("Generating report by following params:\n Table Category : {}, Column category {} , Row category : {}, Thea measure : {}",
                tableCategory, columnCategory, rowCategory, measure);
        ReportObject reportObject = queryService.retrieveReportData(tableCategory, columnCategory, rowCategory, measure);
        List<TableGuiModel> tableGuiModel = iterationService.parseReportObjectToGuiModel(reportObject);
        File generatedReport = htmlReportGenerator.generateHTMLReport(tableGuiModel);
//        Resource generatedFile = new FileSystemResource();
        //        return new FileSystemResource(htmlReportGenerator.generateHTMLReport(tableGuiModel));
        log.info("Report has been generated successfully({})", generatedReport.getAbsolutePath());
        return "Report path: " + generatedReport.getAbsolutePath();

    }
}
