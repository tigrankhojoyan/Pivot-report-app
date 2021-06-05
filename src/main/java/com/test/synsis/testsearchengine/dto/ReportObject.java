package com.test.synsis.testsearchengine.dto;

import lombok.Data;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Data
public class ReportObject {
    private Map<Object, List<ColumnRawMeasure>> reportData = new TreeMap<>();
}
