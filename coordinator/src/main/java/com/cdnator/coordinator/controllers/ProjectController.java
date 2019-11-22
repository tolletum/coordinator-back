package com.cdnator.coordinator.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.cdnator.coordinator.entities.Project;
import com.cdnator.coordinator.exception.EntityNotFoundException;
import com.cdnator.coordinator.repositories.ProjectRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/coordinators")
public class ProjectController {

  private static final Logger logger = LoggerFactory.getLogger(ProjectController.class);

  @Autowired
  private ProjectRepository repository;

  @PostMapping("/projects")
  public ResponseEntity<Project> insertProject(@Valid @RequestBody Project project) {

    logger.debug("--> Entro en el método insertPrject");

    final Project savedProject = repository.save(project);

    URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedProject.getId())
        .toUri();

    logger.debug("--> Salgo del método insertProject");

    return ResponseEntity.created(location).body(savedProject);
  }

  @GetMapping("/projects")
  public List<Project> listProjects() {

    logger.debug("--> Entro en el método listProjects");

    final List<Project> listOfProjects = repository.findAll();

    logger.debug("<-- Salgo del método listProjects");

    return listOfProjects;
  }

  @GetMapping("/projects/{id}")
  public ResponseEntity<Project> getProject(@PathVariable String id) {

    logger.debug("--> Entro en el método getProject");

    final Optional<Project> project = repository.findById(id);

    if (project.isPresent()) {
      logger.debug(" <-- Salgo del método getProject");
      return ResponseEntity.ok().body(project.get());
    } else {
      logger.error("ERROR: project not found with id %s ", id);
      throw new EntityNotFoundException("Project not found with id: " + id);
    }

  }

  @PatchMapping("/projects/{id}")
  public ResponseEntity<Project> updateProject(@PathVariable String id, @RequestBody Project updatedProject) {

    logger.debug(" --> Entro en el método updateProject");

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

    logger.debug(" <-- Salgo del método updateProject");

    return ResponseEntity.ok().body(savedProject);
  }

}