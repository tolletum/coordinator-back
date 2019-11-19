package com.cdnator.coordinator.repositories;

import com.cdnator.coordinator.entities.Project;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, String> {

}