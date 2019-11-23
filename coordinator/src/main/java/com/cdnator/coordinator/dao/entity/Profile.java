package com.cdnator.coordinator.dao.entity;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.hibernate.annotations.GenericGenerator;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "PROFILE", schema = "COORDINATOR")
@Data
@NoArgsConstructor
public class Profile {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @NotNull
    @NotBlank
    private String description;

    @NotNull
    @Min(0)
    private BigDecimal rate;

    @OneToMany(mappedBy = "profile")
    @JsonIgnoreProperties("profile")
    private List<Employee> employees;

    public Profile(String description, BigDecimal rate) {
        super();
        this.description = description;
        this.rate = rate;
    }

}