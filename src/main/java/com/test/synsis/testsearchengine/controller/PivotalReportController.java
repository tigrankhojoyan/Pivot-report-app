package com.test.synsis.testsearchengine.controller;

import com.test.synsis.testsearchengine.dto.ReportObject;
import com.test.synsis.testsearchengine.dto.TableGuiModel;
import com.test.synsis.testsearchengine.service.IterationService;
import com.test.synsis.testsearchengine.service.QueryService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Slf4j
@AllArgsConstructor
@Controller
@RequestMapping("/")
public class PivotalReportController {

    private final QueryService queryService;
    private final IterationService iterationService;

    @GetMapping("/")
    public String index() {
//        model.addAttribute("tableCategory", "");
//        model.addAttribute("columnCategory", "");
//        model.addAttribute("rowCategory", "");
//        model.addAttribute("measure", "");
        return "index";
    }


    @GetMapping("/search")
    public String generateReport(@RequestParam("tableCategory") String tableCategory,
                                 @RequestParam("columnCategory") String columnCategory,
                                 @RequestParam("rowCategory") String rowCategory,
                                 @RequestParam("measure") String measure, final Model model) {
        log.info("Generating report by following params:\n Table Category : {}, Column category {} , Row category : {}, Thea measure : {}",
                tableCategory, columnCategory, rowCategory, measure);

        ReportObject reportObject = queryService.retrieveReportData(tableCategory, columnCategory, rowCategory, measure);
        iterationService.printPivotalReport(reportObject);
        List<TableGuiModel> tableGuiModel = iterationService.parseReportObjectToGuiModel(reportObject);
        model.addAttribute("tableCategory", tableCategory);
        model.addAttribute("columnCategory", columnCategory);
        model.addAttribute("rowCategory", rowCategory);
        model.addAttribute("measure", measure);
        model.addAttribute("tableGuiModel", tableGuiModel);
        log.info(tableGuiModel.toString());
        return "index";
    }


}
