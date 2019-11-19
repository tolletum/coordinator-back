package com.cdnator.coordinator.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import com.cdnator.coordinator.entities.Team;
import com.cdnator.coordinator.exception.EntityNotFoundException;
import com.cdnator.coordinator.repositories.TeamRepository;
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
public class TeamController {

    @Autowired
    private TeamRepository repository;

    @PostMapping("/teams")
    public ResponseEntity<Team> insertTeam(@Valid @RequestBody Team team) {
  
      final Team savedTeam = repository.save(team);
  
      URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
          .buildAndExpand(savedTeam.getId()).toUri();
  
      return ResponseEntity.created(location).body(savedTeam);
    }
  
    @GetMapping("/teams")
    public List<Team> listTeams() {
  
      List<Team> listOfTeams = repository.findAll();
  
      return listOfTeams;
    }
  
    @GetMapping("/teams/{id}")
    public ResponseEntity<Team> getTeam(@PathVariable String id) {
  
      Optional<Team> team = repository.findById(id);
  
      if (team.isPresent()) {
        return ResponseEntity.ok().body(team.get());
      } else {
        throw new EntityNotFoundException("Team not found with id: " + id);
      }
    }
  
    @PatchMapping("/teams/{id}")
    public ResponseEntity<Team> updateTeam(@PathVariable String id, @RequestBody Team updatedTeam) {
  
      Optional<Team> existentTeam = repository.findById(id);
      if (!existentTeam.isPresent()) {
        throw new EntityNotFoundException("Team not found with id: " + id);
      }
  
      if(updatedTeam.getDescription() != null) {
        existentTeam.get().setDescription(updatedTeam.getDescription());
      }
  
      if(updatedTeam.getArea() != null) {
        existentTeam.get().setArea(updatedTeam.getArea());
      }
      
      Team savedTeam = repository.save(existentTeam.get());
  
      return ResponseEntity.ok().body(savedTeam);
    }
 
}