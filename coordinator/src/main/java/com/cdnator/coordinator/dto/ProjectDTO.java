package com.cdnator.coordinator.dto;

import java.math.BigDecimal;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProjectDTO {

  private String id;

  @NotEmpty
  private String name;

  @NotEmpty
  private String description;

  @NotNull
  private BigDecimal budget;

  @JsonIgnoreProperties("projects")
  private TeamDTO team;

  @NotEmpty
  private String client;

  @NotEmpty
  private String manager;

}