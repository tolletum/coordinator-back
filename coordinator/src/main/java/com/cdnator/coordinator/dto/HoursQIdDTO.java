package com.cdnator.coordinator.dto;

import com.cdnator.coordinator.dto.enumtypes.MonthEnum;
import com.cdnator.coordinator.dto.enumtypes.QuarterEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class HoursQIdDTO {

    private String year;

    private QuarterEnum quarter;

    private MonthEnum month;

    private EmployeeDTO employee;
}
