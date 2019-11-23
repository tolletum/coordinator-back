package com.cdnator.coordinator.dto;

import java.util.List;
import java.util.UUID;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TeamDTO {

    private UUID id;

    @NotEmpty
    private String description;

    @NotEmpty
    private String area;

    @JsonIgnoreProperties("team")
    private List<EmployeeDTO> employees;

    @JsonIgnoreProperties("team")
    private List<ProjectDTO> projects;

}