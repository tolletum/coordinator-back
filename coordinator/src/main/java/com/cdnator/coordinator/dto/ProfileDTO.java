package com.cdnator.coordinator.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProfileDTO {

    private UUID id;

    @NotNull
    @NotBlank
    private String description;

    @NotNull
    @Min(0)
    private BigDecimal rate;

    @JsonIgnoreProperties("profile")
    private List<EmployeeDTO> employees;

    public ProfileDTO(String description, BigDecimal rate) {
        super();
        this.description = description;
        this.rate = rate;
    }

}