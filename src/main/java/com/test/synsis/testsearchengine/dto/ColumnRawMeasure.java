package com.test.synsis.testsearchengine.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ColumnRawMeasure {
    private String columnCategory;
    private String rowCategory;
    private String measurement;
}
