package com.cdnator.coordinator.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import com.cdnator.coordinator.dao.ProjectDao;
import com.cdnator.coordinator.dao.entity.Project;
import com.cdnator.coordinator.dto.ProjectDTO;
import com.cdnator.coordinator.mapper.MapperDTO;

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
  private ProjectDao dao;

  @Autowired
  private MapperDTO mapper;

  @PostMapping("/projects")
  public ResponseEntity<ProjectDTO> insertProject(@Valid @RequestBody final ProjectDTO project) {

    final Project savedProject = dao.insertProject(mapper.projectDTOToProject(project));

    final URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
        .buildAndExpand(savedProject.getId()).toUri();

    return ResponseEntity.created(location).body(mapper.projectToProjectDTO(savedProject));
  }

  @GetMapping("/projects")
  public List<ProjectDTO> listProjects() {

    final List<Project> listOfProjects = dao.listProjects();

    return mapper.listProjectToProjectDTO(listOfProjects);
  }

  @GetMapping("/projects/{id}")
  public ResponseEntity<ProjectDTO> getProject(@PathVariable final String id) {

    final Project project = dao.getProject(id);

    return ResponseEntity.ok().body(mapper.projectToProjectDTO(project));
  }

  @PatchMapping("/projects/{id}")
  public ResponseEntity<ProjectDTO> updateProject(@PathVariable final String id,
      @RequestBody final ProjectDTO updatedProject) {

    final Project savedProject = dao.updateProject(id, mapper.projectDTOToProject(updatedProject));

    return ResponseEntity.ok().body(mapper.projectToProjectDTO(savedProject));
  }

}