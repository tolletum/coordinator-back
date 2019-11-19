package com.cdnator.coordinator.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.cdnator.coordinator.entities.Project;
import com.cdnator.coordinator.exception.EntityNotFoundException;
import com.cdnator.coordinator.repositories.ProjectRepository;

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

  @Autowired
  private ProjectRepository repository;

  @PostMapping("/projects")
  public ResponseEntity<Project> insertProject(@Valid @RequestBody Project project) {

    final Project savedProject = repository.save(project);

    URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedProject.getId())
        .toUri();

    return ResponseEntity.created(location).body(savedProject);
  }

  @GetMapping("/projects")
  public List<Project> listProjects() {

    List<Project> listOfProjects = repository.findAll();

    return listOfProjects;
  }

  @GetMapping("/projects/{id}")
  public ResponseEntity<Project> getProject(@PathVariable String id) {

    Optional<Project> project = repository.findById(id);

    if (project.isPresent()) {
      return ResponseEntity.ok().body(project.get());
    } else {
      throw new EntityNotFoundException("Project not found with id: " + id);
    }
  }

  @PatchMapping("/projects/{id}")
  public ResponseEntity<Project> updateProject(@PathVariable String id, @RequestBody Project updatedProject) {

    Optional<Project> existentProject = repository.findById(id);
    if (!existentProject.isPresent()) {
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

    Project savedProject = repository.save(existentProject.get());

    return ResponseEntity.ok().body(savedProject);
  }

}