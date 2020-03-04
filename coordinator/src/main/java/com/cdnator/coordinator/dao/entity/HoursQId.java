package com.cdnator.coordinator.dao.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Embeddable
public class HoursQId implements Serializable {

    /**
   *
   */
  private static final long serialVersionUID = 7862185116005158964L;

    private String year;

    private String quarter;

    private String month;

    @OneToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name="employeeid")
    private Employee employee;

}