package com.cdnator.coordinator.dao.repository;

import com.cdnator.coordinator.dao.entity.Project;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, String> {

}