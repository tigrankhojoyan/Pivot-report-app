package com.test.synsis.testsearchengine.dto;

public enum Measure {
    TOTAL_COAST("totalCost"),
    COUNT("count");

    private final String measureValue;

    Measure(String measureValue) {
        this.measureValue = measureValue;
    }

    public String getMeasureValue() {
        return measureValue;
    }

    public static Measure findMeasureByValue(String value) {
        for (Measure measure : Measure.values()) {
            if (measure.measureValue.equalsIgnoreCase(value)) {
                return measure;
            }
        }
        return null;
    }
}
