package com.cdnator.coordinator.dao.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "EMPLOYEE", schema = "COORDINATOR")
@Data
@NoArgsConstructor
public class Employee {

  @Id
  @NotNull
  @NotBlank
  private String id;

  @NotNull
  @NotBlank
  private String name;

  @NotNull
  @NotBlank
  @Column(name = "lastname")
  private String lastName;

  @Max(100)
  @Min(0)
  private Integer chargeability;

  @Column(name = "iscoordinator")
  private Boolean isCoordinator;

  @ManyToOne(cascade = CascadeType.REFRESH)
  @JoinColumn(name = "profileid")
  @JsonIgnoreProperties("employees")
  private Profile profile;

  @ManyToOne(cascade = CascadeType.REFRESH)
  @JoinColumn(name = "teamid")
  @JsonIgnoreProperties("employees")
  private Team team;

}