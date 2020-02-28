package com.cdnator.coordinator.dto.enumtypes;

public enum QuarterEnum {
    Q1("Q1"), Q2("Q2"), Q3("Q3"), Q4("Q4");

    private String quarter;

    QuarterEnum(String quarter) {
        this.quarter = quarter;
    }

    public String getQuarter() {
        return this.quarter;
    }
}