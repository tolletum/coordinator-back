package com.cdnator.coordinator.dao;

import java.util.List;
import java.util.Optional;

import com.cdnator.coordinator.dao.entity.Project;
import com.cdnator.coordinator.dao.repository.ProjectRepository;
import com.cdnator.coordinator.exception.EntityNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProjectDao {

  private static final Logger logger = LoggerFactory.getLogger(ProjectDao.class);

  @Autowired
  private ProjectRepository repository;

  public Project insertProject(final Project project) {

    final Project savedProject = repository.save(project);

    return savedProject;
  }

  public List<Project> listProjects() {

    final List<Project> listOfProjects = repository.findAll();

    return listOfProjects;
  }

  public Project getProject(final String id) {

    final Optional<Project> project = repository.findById(id);

    if (project.isPresent()) {
      return project.get();
    } else {
      logger.error("ERROR: project not found with id %s ", id);
      throw new EntityNotFoundException("Project not found with id: " + id);
    }

  }

  public Project updateProject(final String id, final Project updatedProject) {

    final Optional<Project> existentProject = repository.findById(id);

    if (!existentProject.isPresent()) {
      logger.error("ERROR: Project not found with id %s", id);
      throw new EntityNotFoundException("Project not found with id: " + id);
    }

    if (updatedProject.getBudget() != null) {
      existentProject.get().setBudget(updatedProject.getBudget());
    }

    if (updatedProject.getClient() != null) {
      existentProject.get().setClient(updatedProject.getClient());
    }

    if (updatedProject.getDescription() != null) {
      existentProject.get().setDescription(updatedProject.getDescription());
    }

    if (updatedProject.getManager() != null) {
      existentProject.get().setManager(updatedProject.getManager());
    }

    final Project savedProject = repository.save(existentProject.get());

    return savedProject;
  }

  public void deleteProject(String id) {

    repository.deleteById(id);
  }

}