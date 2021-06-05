package com.test.synsis.testsearchengine.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TableGuiModel {
    private String tableCategory;
    private List<List<String>> rowColumnValues = new ArrayList<>();
}
