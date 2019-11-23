package com.cdnator.coordinator.dao.entity;

import java.util.List;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TEAM", schema = "COORDINATOR")
@Data
@NoArgsConstructor
public class Team {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @NotEmpty
    private String description;

    @NotEmpty
    private String area;

    @OneToMany(mappedBy = "team")
    @JsonIgnoreProperties("team")
    private List<Employee> employees;

    @OneToMany(mappedBy = "team")
    @JsonIgnoreProperties("team")
    private List<Project> projects;

}