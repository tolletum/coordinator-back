package com.cdnator.coordinator.entities;

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

@Entity
@Table(name = "EMPLOYEE", schema = "COORDINATOR")
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

  @ManyToOne(cascade = CascadeType.PERSIST)
  @JoinColumn(name = "profileid")
  @JsonIgnoreProperties("employees")
  private Profile profile;

  @ManyToOne(cascade = CascadeType.PERSIST)
  @JoinColumn(name = "teamid")
  @JsonIgnoreProperties("employees")
  private Team team;

  public Employee() {
    super();
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public Integer getChargeability() {
    return chargeability;
  }

  public void setChargeability(Integer chargeability) {
    this.chargeability = chargeability;
  }

  public Boolean getIsCoordinator() {
    return isCoordinator;
  }

  public void setIsCoordinator(Boolean isCoordinator) {
    this.isCoordinator = isCoordinator;
  }

  public Profile getProfile() {
    return profile;
  }

  public void setProfile(Profile profile) {
    this.profile = profile;
  }

  public Team getTeam() {
    return team;
  }

  public void setTeam(Team team) {
    this.team = team;
  }

}