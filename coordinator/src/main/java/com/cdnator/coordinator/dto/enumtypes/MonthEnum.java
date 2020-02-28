package com.cdnator.coordinator.dto.enumtypes;

public enum MonthEnum {
    ENERO("ENERO"),
    FEBRERO("FEBRERO"),
    MARZO("MARZO"),
    ABRIL("ABRIL"),
    MAYO("MAYO"),
    JUNIO("JUNIO"),
    JULIO("JULIO"),
    AGOSTO("AGOSTO"),
    SEPTIEMBRE("SEPTIEMBRE"),
    OCTUBRE("OCTUBRE"),
    NOVIEMBRE("NOVIEMBRE"),
    DICIEMBRE("DICIEMBRE");

    private String month;

    private MonthEnum(String month) {
        this.month = month;
    }

    public String getMonth() {
        return this.month;
    }
}