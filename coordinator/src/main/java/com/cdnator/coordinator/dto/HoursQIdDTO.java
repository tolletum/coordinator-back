package com.cdnator.coordinator.dto;

import com.cdnator.coordinator.dto.enumtypes.MonthEnum;
import com.cdnator.coordinator.dto.enumtypes.QuarterEnum;
import ch.qos.logback.core.joran.spi.NoAutoStart;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class HoursQIdDTO {

    @NonNull
    private String year;

    @NonNull
    private QuarterEnum quarter;

    @NonNull
    private MonthEnum month;

    @NonNull
    private EmployeeDTO employee;
}
