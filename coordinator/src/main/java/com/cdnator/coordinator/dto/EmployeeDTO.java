package com.cdnator.coordinator.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EmployeeDTO {

  @NotNull
  @NotBlank
  private String id;

  @NotNull
  @NotBlank
  private String name;

  @NotNull
  @NotBlank
  private String lastName;

  @Max(100)
  @Min(0)
  private Integer chargeability;

  private Boolean isCoordinator;

  @JsonIgnoreProperties("employees")
  private ProfileDTO profile;

  @JsonIgnoreProperties("employees")
  private TeamDTO team;

}