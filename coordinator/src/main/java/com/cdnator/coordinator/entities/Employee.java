package com.cdnator.coordinator.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "EMPLOYEES", schema = "COORDINATOR")
public class Employee {

  @Id
  @GeneratedValue(generator = "UUID")
  @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
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

  private String profile;

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

  public String getProfile() {
    return profile;
  }

  public void setProfile(String profile) {
    this.profile = profile;
  }

}