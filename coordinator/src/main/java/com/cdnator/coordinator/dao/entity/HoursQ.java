package com.cdnator.coordinator.dao.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.FieldResult;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "HOURS_Q", schema = "COORDINATOR")
@SqlResultSetMapping(
    name="HoursQMapping",
    entities={
      @EntityResult(entityClass=com.cdnator.coordinator.dao.entity.HoursQ.class,
        fields={
            @FieldResult(name="id.year", column="year"),
            @FieldResult(name="id.quarter", column="quarter"),
            @FieldResult(name="id.month", column="month"),
            @FieldResult(name="id.employee", column="employeeid"),
            @FieldResult(name="hours", column="hours"),
        }
      )
    }
)
public class HoursQ {

    @EmbeddedId
    private HoursQId id;

    private int hours;

    
}