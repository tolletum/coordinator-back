package com.cdnator.coordinator.dao.entity;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "PROJECT", schema = "COORDINATOR")
@Data
@NoArgsConstructor
public class Project {

  @Id
  private String id;

  @NotEmpty
  private String name;

  @NotEmpty
  private String description;

  @NotNull
  private BigDecimal budget;

  @ManyToOne(cascade = CascadeType.PERSIST)
  @JoinColumn(name = "teamid")
  @JsonIgnoreProperties("projects")
  private Team team;

  @NotEmpty
  private String client;

  @NotEmpty
  private String manager;

}